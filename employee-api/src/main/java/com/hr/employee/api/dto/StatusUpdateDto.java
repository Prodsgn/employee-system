package com.hr.employee.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class StatusUpdateDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1065462492480352269L;


	private Long id;
	private String statusCode;

	public StatusUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusUpdateDto(Long id, String statusCode) {
		this.id = id;
		this.statusCode = statusCode;
	}

}
