package com.hr.employee.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface BaseController <T , DTO , ID> {

	@GetMapping("/")
	ResponseEntity findAll();

	@GetMapping("/page")
	ResponseEntity findAllPaging(@RequestParam Optional<Integer> pageNum ,@RequestParam Optional<Integer> size ,
			@RequestParam Optional<String> columnName);

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	ResponseEntity findById(@PathVariable ID id);
	/**
	 *
	 * @param dto
	 * @return
	 */
	@PostMapping("/")
	ResponseEntity insert (@RequestBody DTO dto);
	/**
	 *
	 * @param entities
	 * @return
	 */
	@PostMapping("/all")
	ResponseEntity insertAll(@RequestBody List<DTO> entities);
	/**
	 *
	 * @param dto
	 * @return
	 */
	@PutMapping("/")
	ResponseEntity update (@RequestBody DTO dto);

	/**
	 *
	 * @param entities
	 * @return
	 */
	@PutMapping("/all")
	ResponseEntity updateAll(@RequestBody List<DTO> entities);

	/**
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/")
	ResponseEntity<Void> deleteById (@RequestParam ID id);
	/**
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/all")
	ResponseEntity<Void> deleteAll (@RequestParam List<ID> ids);

}
