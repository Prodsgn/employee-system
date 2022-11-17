package com.hr.employee.api.controller;


import com.hr.employee.api.dto.StatusUpdateDto;
import com.hr.employee.api.dto.SuccessResponseDto;
import com.hr.employee.api.mapper.BaseMapper;
import com.hr.employee.api.model.BaseEntity;
import com.hr.employee.api.service.impl.BaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@MappedSuperclass
@CrossOrigin(exposedHeaders = { "Etag" })
@Controller
public abstract class BaseControllerImpl<T extends BaseEntity<ID>, DTO, ID extends Number>
		implements BaseController<T, DTO, ID> {

	@Autowired
	private BaseServiceImpl<T, ID> baseService;

	@Autowired
	private BaseMapper<T, DTO> baseMapper;

	@Override
	@GetMapping("")
	@Operation(summary = "Find All", description = "to retrieve list of all data")
	public ResponseEntity<?> findAll() {
		List<DTO> dtos = baseMapper.map(baseService.findAll());
		return ResponseEntity.ok(new SuccessResponseDto<>(dtos));
	}

	@Override
	@GetMapping("/page")
	@Operation(summary = "Find All Pageble", description = "paramters is  1-pageNum int, optional, default value is 0"
			+ ", 2-size int, optional, default value is 10 , 3-sortableColumn string, optional, default value is nameAr")
	public ResponseEntity findAllPaging(@RequestParam Optional<Integer> pageNum, @RequestParam Optional<Integer> size,
			@RequestParam Optional<String> sortableColumn) {
		Pageable pageable = PageRequest.of(pageNum.orElseGet(() -> 0), size.orElseGet(() -> 10),
				Sort.by(sortableColumn.orElseGet(() -> "nameAr")));
		Page<DTO> dtos = baseService.findAll(pageable).map(t -> baseMapper.map(t));
		return ResponseEntity.ok(new SuccessResponseDto(dtos));
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	@GetMapping("/{id}")
	@Operation(summary = "Find by id", description = "to retrieve element by id")
	public ResponseEntity<?> findById(@PathVariable ID id) {
		return ResponseEntity.ok(new SuccessResponseDto<DTO>(baseMapper.map(baseService.findById(id))));
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	@Override
	@PostMapping("")
	@Operation(summary = "Insert", description = "to adding one element by json object")
	public ResponseEntity insert(@Valid @RequestBody DTO dto) {
		T entity = baseMapper.unMap(dto);
		T result = baseService.insert(entity);
		DTO dtos = baseMapper.map(result);
		return new ResponseEntity<SuccessResponseDto<DTO>>(new SuccessResponseDto<DTO>(dtos), HttpStatus.CREATED);
	}

	/**
	 *
	 * @param dtos
	 * @return
	 */
	@Override
	@PostMapping("/all")
	@Operation(summary = "Insert All", description = "to adding mulit elements by array of json objects", hidden = true)
	public ResponseEntity insertAll(@Valid @RequestBody List<DTO> dtos) {
		List<T> entities = baseMapper.unMap(dtos);
		return ResponseEntity.ok(new SuccessResponseDto(baseService.saveAll(entities)));
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	@Override
	@PutMapping("")
	@Operation(summary = "Update", description = "to updating one element by json object")
	public ResponseEntity update(@Valid @RequestBody DTO dto) {
		T entity = baseMapper.unMap(dto);
		T result = baseService.update(entity);
		DTO dtos = baseMapper.map(result);
		return new ResponseEntity<SuccessResponseDto<DTO>>(new SuccessResponseDto<DTO>(dtos), HttpStatus.CREATED);
	}

	/**
	 *
	 * @param dtos
	 * @return
	 */
	@Override
	@PutMapping("/all")
	@Operation(summary = "Update All", description = "to updating mulit elements by array of json objects", hidden = true)
	public ResponseEntity updateAll(@Valid @RequestBody List<DTO> dtos) {
		List<T> entities = baseMapper.unMap(dtos);
		return ResponseEntity.ok(new SuccessResponseDto(baseService.saveAll(entities)));
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	@DeleteMapping("")
	@Operation(summary = "Delete", description = "to delete one element by id")
	public ResponseEntity deleteById(@RequestParam ID id) {
		baseService.deleteById(id);
		return ResponseEntity.ok(new SuccessResponseDto<T>(null));
	}

	/**
	 *
	 */
	@Override
	@DeleteMapping("all")
	@Operation(summary = "Delete All", description = "to delete array of elements by ids", hidden = true)
	public ResponseEntity deleteAll(List<ID> ids) {
		baseService.deleteAll(ids);
		return ResponseEntity.ok(new SuccessResponseDto<T>(null));
	}

	/**
	 * Update Status code
	 * @param statusUpdateDto
	 * @return
	 */
	@PutMapping("/update-status")
	@Operation(summary = "Update", description = "Update Status code 1- draft ....")
	public ResponseEntity updateStatus(@RequestBody StatusUpdateDto statusUpdateDto) {
		return ResponseEntity.ok(new SuccessResponseDto(baseService.updateStatus(statusUpdateDto)));
	}

}
