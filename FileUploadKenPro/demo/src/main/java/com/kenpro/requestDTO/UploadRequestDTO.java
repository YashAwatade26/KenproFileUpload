package com.kenpro.requestDTO;

import java.time.LocalDateTime;
import java.util.List;
import com.kenpro.customvalidations.CustomDateValidator;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UploadRequestDTO {
	
	 @NotEmpty(message = "Received from list cannot be empty")
	    private List<String> receivedFrom;

	    @NotEmpty(message = "Received date-time list cannot be empty")
	    @CustomDateValidator
	    private List<LocalDateTime> receivedDateTime;

	    @NotEmpty(message = "Modified date-time list cannot be empty")
	    @CustomDateValidator
	    private List<LocalDateTime> modifiedDateTime;

	    @NotEmpty(message = "Description list cannot be empty")
	    private List<String> description;


}
