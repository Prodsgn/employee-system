/**
 *
 */
package com.hr.employee.api.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 *
 * @author nourshaheen
 *
 */
@Getter
public class ErrorResponseDto extends BaseResponseDto {

	/**
	 *
	 */
	private static final long serialVersionUID = 4716480749998397552L;

	private final String message;
	private  String code;
	private final String exception;
	private final List<String> details;
	private final LocalDateTime timestamp;

	public ErrorResponseDto(HttpStatus status, String message , String exception , List<String> details) {
		super(Boolean.FALSE, status);
		this.message = message;
		this.exception = exception;
		this.details = details;
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponseDto(HttpStatus status, String code, String message , String exception , List<String> details) {
		super(Boolean.FALSE, status);
		this.code = code ;
		this.message = message;
		this.exception = exception;
		this.details = details;
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponseDto(HttpStatus status, String message , String exception , String detail) {
		super(Boolean.FALSE, status);
		this.message = message;
		this.exception = exception ;
		this.details = Collections.singletonList(detail);
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponseDto(HttpStatus status, String code , String message , String exception , String detail) {
		super(Boolean.FALSE, status);
		this.message = message;
		this.code = code ;
		this.exception = exception ;
		this.details = Collections.singletonList(detail);
		this.timestamp = LocalDateTime.now();
	}

	public static ErrorResponseBuilder status(HttpStatus status) {
		return new ErrorResponseBuilder(status);
	}

	public static ErrorResponseDto unauthorized(String message, List<String> details) {
		return status(UNAUTHORIZED, message, details);
	}

	public static ErrorResponseDto forbidden(String message, List<String> details) {
		return status(FORBIDDEN, message, details);
	}

	public static ErrorResponseDto status(HttpStatus status, String message, List<String> details) {
		return status(status).message(message).details(details).build();
	}

	public static class ErrorResponseBuilder {
		private String message;
		private String exception;
		private List<String> details;
		private final HttpStatus status;

		public ErrorResponseBuilder(HttpStatus status) {
			this.status = status;
			this.details = new ArrayList<>();
		}

		public ErrorResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public ErrorResponseBuilder exception(String exception) {
			this.exception = exception;
			return this;
		}

		public ErrorResponseBuilder details(List<String> details) {
			this.details = details;
			return this;
		}

		public ErrorResponseDto build() {
			return new ErrorResponseDto(this.status, this.message, this.exception, this.details);
		}
	}
}
