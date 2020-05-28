package com.yearup.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yearup.dto.error.ErrorDetail;
import com.yearup.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(ex.getMessage());
		errorDetail.setDeveloperMessage(ex.getClass().getName());

		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}
	
	@Override
	 public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(status.value());
		errorDetail.setTitle("Message Not Readable");
		errorDetail.setDetail(manve.getMessage());
		errorDetail.setDeveloperMessage(manve.getClass().getName());

		return handleExceptionInternal(manve, errorDetail, headers, status, request);
	 }
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
		
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Resource Not Found");
		errorDetail.setDetail(rnfe.getMessage());
		errorDetail.setDeveloperMessage(rnfe.getClass().getName());
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
//		
//		ErrorDetail errorDetail = new ErrorDetail();
//		// Populate errorDetail instance
//		errorDetail.setTimeStamp(new Date().getTime());
//		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//		String requestPath = (String) request.getAttribute("javax.servlet.error.request.uri");
//		if(requestPath == null) {
//			requestPath = request.getRequestURI();
//		}
//		errorDetail.setTitle("Validation Failed");
//		errorDetail.setDetail("Input validation failed");
//		errorDetail.setDeveloperMessage(manve.getClass().getName());
//		
//		// Create ValidationError instances
//		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//		for(FieldError fe: fieldErrors) {
//			
//			List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
//			if(validationErrorList == null) {
//				validationErrorList = new ArrayList<>();
//				errorDetail.getErrors().put(fe.getField(), validationErrorList);
//			}
//			ValidationError validationError = new ValidationError();
//			validationError.setCode(fe.getCode());
//			validationError.setMessage(messageSource.getMessage(fe, null));
//			validationErrorList.add(validationError);
//		}
//		return errorDetail;
//	}

}
