package com.hr.employee.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nourshaheen
 *
 * @param <T>
 * @param <ID>
 */
@MappedSuperclass
public interface BaseService<T , ID > {
	/**
	 *
	 * @return
	 */
	List<T> findAll();
	/**
	 *
	 */
	Page<T> findAll (Pageable pageable);

	/**
	 *
	 * @param id
	 * @return
	 */
	T findById(ID id);

	/**
	 *
	 * @param id
	 * @return
	 */
	T getReferenceById(ID id);

	/**
	 *
	 * @param id
	 * @return
	 */
	Optional<T> getById(ID id);
	/**
	 *
	 * @param entity
	 * @return
	 */
	T insert (T entity);
	/**
	 *
	 * @param entity
	 * @return
	 */
	T persist (T entity);
	/**
	 *
	 * @param entity
	 * @return
	 */
	T update (T entity);
	/**
	 *
	 * @param entities
	 * @return
	 */
	List<T> saveAll(List<T> entities);
	/**
	 *
	 * @param id
	 * @return
	 */
	void deleteById (ID id);
	/**
	 *
	 * @param ids
	 */
	void deleteAll (List<ID> ids);


}
