package com.hr.employee.api.error;

import com.hr.employee.api.dto.ErrorResponseDto;
import com.hr.employee.api.service.impl.MessageSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

	private final MessageSourceService messageSourceService;


	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Object> handleBusinessException(BusinessException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(messageSourceService.getMessage("exception.business"));
		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.EXPECTATION_FAILED, ex.getLocalizedMessage() ,ex.toString(), details);
		return new ResponseEntity<>(error, error.getStatus());
	}



	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		String msg = ex.getLocalizedMessage();  //messageSourceService.getMessage("exception.record.not-found");
		details.add(msg);
		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.NOT_FOUND, msg, ex.getMessage(), details);
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(DuplicateRecordException.class)
	public final ResponseEntity<Object> handleDuplicateRecordException(DuplicateRecordException ex,
																	   WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(messageSourceService.getMessage("exception.duplicate.record"));
		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.CONFLICT, ex.getLocalizedMessage() ,ex.toString(), details);
		return new ResponseEntity<>(error, error.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getDefaultMessage());
		}

		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.BAD_REQUEST, messageSourceService.getMessage("exception.validation.error"),
				ex.toString(), errors);
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

		ErrorResponseDto respose = new ErrorResponseDto(HttpStatus.FORBIDDEN,
				messageSourceService.getMessage("security.access-denied"),ex.toString(), ex.getMessage());

		return new ResponseEntity<>(respose, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleSQLIntegrityConstraint(DataIntegrityViolationException ex, WebRequest request) {
		ex.printStackTrace();
		log.error( "Message >>"  + ex.getMessage());
		log.error( "LocalizedMessage >>"  + ex.getLocalizedMessage());
		ErrorResponseDto respose = new ErrorResponseDto(HttpStatus.CONFLICT,
				messageSourceService.getMessage("data.integrity.violation.exception"),ex.toString(), ex.getMessage());

		return new ResponseEntity<>(respose, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		log.error("Application error in: [" + ex.getClass().getName() + "]", ex);
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponseDto error = new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,
				messageSourceService.getMessage("exception.server.error"),
				ex.toString(), details);
		return new ResponseEntity<>(error, error.getStatus());
	}
}
