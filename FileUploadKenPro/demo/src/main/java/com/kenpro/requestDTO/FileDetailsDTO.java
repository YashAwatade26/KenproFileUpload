package com.kenpro.requestDTO;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDetailsDTO {
		private Long id;
	    private String documentName;
	    private String description;
	    private String receivedFrom;
	    private LocalDateTime uploadDate;
}
