/**
 *
 */
package com.hr.employee.api.dto;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.http.HttpStatus.OK;

/**
 *
 * @author nourshaheen
 *
 * @param <T>
 */
@Getter
@Setter
public class SuccessResponseDto<T> extends BaseResponseDto {

	private T data;

	public SuccessResponseDto(T data) {
		super(Boolean.TRUE, OK);
		this.data = data;
	}

	public static <T> SuccessResponseDto<T> ok(T data) {
		return new SuccessResponseDto<>(data);
	}
}
