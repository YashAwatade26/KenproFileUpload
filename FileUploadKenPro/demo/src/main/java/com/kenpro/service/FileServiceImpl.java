package com.kenpro.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kenpro.pojos.FileDetails;
import com.kenpro.repository.FileRepository;
import com.kenpro.requestDTO.FileDetailsDTO;

import co.kenpro.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fileRepository;

	@Autowired
	ModelMapper modelMapper;

	public void saveDocuments(List<MultipartFile> files, List<String> receivedFromList,
			List<LocalDateTime> receivedDateTimeList, List<LocalDateTime> modifiedDateTimeList,
			List<String> description) {

// Extract file names from the incoming request
		List<String> incomingFileNames = files.stream().map(MultipartFile::getOriginalFilename).toList();

// Check for duplicate file names in the incoming request
		Set<String> duplicateFilesInRequest = incomingFileNames.stream()
				.filter(name -> Collections.frequency(incomingFileNames, name) > 1).collect(Collectors.toSet());

		if (!duplicateFilesInRequest.isEmpty()) {
			throw new RuntimeException("Duplicate files in request: " + String.join(", ", duplicateFilesInRequest));
		}

// Check for duplicate file names already present in the database
		List<String> existingFileNames = fileRepository.findDocumentNamesByNames(incomingFileNames);

		if (!existingFileNames.isEmpty()) {
			throw new RuntimeException("Duplicate files in database: " + String.join(", ", existingFileNames));
		}

// Process and save files if no duplicates are found
		List<FileDetails> savedDocuments = IntStream.range(0, files.size()).mapToObj(i -> {
			try {
				FileDetails fileDetails = new FileDetails();
				fileDetails.setDocumentName(files.get(i).getOriginalFilename());
				fileDetails.setDocumentType(files.get(i).getContentType());
				fileDetails.setFileData(files.get(i).getBytes());
				fileDetails.setReceivedFrom(receivedFromList.get(i));
				fileDetails.setReceivedDate(receivedDateTimeList.get(i));
				fileDetails.setUploadDate(modifiedDateTimeList.get(i));
				fileDetails.setDescription(description.get(i));
				return fileDetails;
			} catch (IOException e) {
				throw new RuntimeException("Error processing file: " + files.get(i).getOriginalFilename(), e);
			}
		}).collect(Collectors.toList());

		fileRepository.saveAll(savedDocuments);
	}

	public FileDetails getFileDetailsById(Long id) {
		 return fileRepository.findById(id).filter(file -> !file.isDeleted()).orElseThrow(()->new ResourceNotFoundException("File not found with id: " + id));
	}


	@Override
	public Page<FileDetailsDTO> getAllFileDetails(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("uploadDate").descending());
		return fileRepository.findByDeletedFalse(pageable).map(file -> modelMapper.map(file, FileDetailsDTO.class));
	}
	
	
	public void softDeleteFile(Long id) {
	        FileDetails file = fileRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
	        file.setDeleted(true);
	        fileRepository.save(file);
	    }
	
	
	  public Page<FileDetailsDTO> getDeletedFiles(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size, Sort.by("uploadDate").descending());
	        return fileRepository.findByDeletedTrue(pageable).map(file->modelMapper.map(file,FileDetailsDTO.class));
	    }
	  
	  
//	  public FileDetails getFileById(Long id) {
//	        return fileRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("File not found with id: " + id));
//	    }
	  
	  
	  
}
