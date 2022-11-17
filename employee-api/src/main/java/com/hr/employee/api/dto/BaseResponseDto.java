/**
 *
 */
package com.hr.employee.api.dto;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


/**
 *
 * @author nourshaheen
 *
 */
@Hidden
public class BaseResponseDto implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3584010602891575844L;

	private Boolean success;
	private HttpStatus status;

	/**
	 * @param success
	 */
	public BaseResponseDto(Boolean success) {
		super();
		this.success = success;
	}

	/**
	 * @param success
	 * @param status
	 */
	public BaseResponseDto(Boolean success, HttpStatus status) {
		super();
		this.success = success;
		this.status = status;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
