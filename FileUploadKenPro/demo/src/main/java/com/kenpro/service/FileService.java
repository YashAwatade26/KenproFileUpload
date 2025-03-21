package com.kenpro.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.kenpro.pojos.FileDetails;
import com.kenpro.requestDTO.FileDetailsDTO;

public interface FileService {
	public void saveDocuments(List<MultipartFile> files, List<String> receivedFromList, List<LocalDateTime> receivedDateTimeList,List<LocalDateTime> modifiedDateTimeList,List<String>description);
	
	FileDetails getFileDetailsById(Long id);
	
	//void deleteFileDetails(Long id);
	
	Page<FileDetailsDTO> getAllFileDetails(int page, int size);
	
	public void softDeleteFile(Long id);
	
	public Page<FileDetailsDTO> getDeletedFiles(int page, int size);
}
