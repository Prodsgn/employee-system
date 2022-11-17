package com.hr.employee.api.model;

import com.hr.employee.api.enums.StatusCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author nourshaheen
 *
 * @param <ID>
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> extends AuditTrail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3855054033844070951L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Schema(description = "The database generated ID", required = true)
	private ID id;

	@Schema(description = "DRAFT, CONFIRMED, APPROVED, REJECTED, ACTIVE, INACTIVE")
	@Enumerated(EnumType.STRING)
	private StatusCodeEnum statusCode;

	private String comments;

	private int isDeleted;


	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseEntity(ID id) {
		this.id = id;
	}


	}
