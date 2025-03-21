package co.kenpro.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ApiResponseData<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseData<>("error", ex.getMessage()));
	    }
	 
	 @ExceptionHandler(IOException.class)
	    public ResponseEntity<ApiResponseData<String>> handleIOException(IOException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseData<>("error", "File processing error: " + ex.getMessage()));
	    }
	 
	 @ExceptionHandler(MetaDataMismatchException.class)
	    public ResponseEntity<ApiResponseData<String>> handleMetaDataMismatchException(MetaDataMismatchException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseData<>("error",ex.getMessage()));
	    }
	 
	 @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ApiResponseData<String>> handleRuntimeException(RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseData<>("error",ex.getMessage()));
	    }
	 
	  @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponseData<String>> handleGlobalException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseData<>("error","UnExpected error occurs" + ex.getMessage()));
	    }
	  
	  
}
