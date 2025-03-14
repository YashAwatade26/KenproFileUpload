package com.kenpro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.kenpro.pojos.FileDetails;

public interface FileRepository extends JpaRepository<FileDetails, Long> ,PagingAndSortingRepository<FileDetails, Long> {
	
	 @Query("SELECT f.documentName FROM FileDetails f WHERE f.documentName IN :names AND f.deleted = false")
	    List<String> findDocumentNamesByNames(@Param("names") List<String> names);
	 
	 Page<FileDetails> findByDeletedFalse(Pageable pageable);
	 
	 Page<FileDetails> findByDeletedTrue(Pageable pageable);
}
