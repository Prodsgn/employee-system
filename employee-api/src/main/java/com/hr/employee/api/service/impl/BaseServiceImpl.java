package com.hr.employee.api.service.impl;



import com.hr.employee.api.doa.BaseRepository;
import com.hr.employee.api.dto.StatusUpdateDto;
import com.hr.employee.api.enums.StatusCodeEnum;
import com.hr.employee.api.error.RecordNotFoundException;
import com.hr.employee.api.model.BaseEntity;
import com.hr.employee.api.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


/**
 * @param <T>
 * @param <ID>
 * @author nourshaheen
 */
@MappedSuperclass
public abstract class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable> implements BaseService<T, ID> {


	@Autowired
	private BaseRepository<T, ID> baseRepository;

	@Autowired
	private MessageSourceService messageSource;

	@Override
	public List<T> findAll() {
		return baseRepository.findAll();
	}

	/**
	 * @return
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public T findById(ID id) {
		String params [] = {id.toString()};
		String msg = messageSource.getMessage("exception.record.not-found", params) ;
		return baseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(msg));
	}

	@Override
	public Optional<T> getById(ID id) {
		// TODO Auto-generated method stub
		return baseRepository.findById(id);
	}

	/**
	 * @param entity
	 * @return
	 */
	@Override
	public T insert(T entity) {
		entity.setComments(getUserAgent());
		if (entity.getStatusCode() == null)
			entity.setStatusCode(StatusCodeEnum.DRAFT);
		return baseRepository.save(entity);

	}

	/**
	 * @param entity
	 * @return
	 */
	@Override
	public T persist(T entity) {
		entity.setComments(getUserAgent());
		if (entity.getStatusCode() == null)
			entity.setStatusCode(StatusCodeEnum.DRAFT);
		return baseRepository.saveAndFlush(entity);

	}

	/**
	 *
	 */
	@Override
	public T update(T entity) {
		entity.setComments(getUserAgent());
		return baseRepository.save(entity);
	}

	/**
	 * @param entities
	 * @return
	 */
	@Override
	public List<T> saveAll(List<T> entities) {
		entities.forEach(entity -> {
		entity.setComments(getUserAgent());
		if (entity.getStatusCode() == null)
			entity.setStatusCode(StatusCodeEnum.DRAFT);
		});
		return baseRepository.saveAll(entities);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public void deleteById(ID id) {
		baseRepository.deleteById(id);
	}

	/**
	 * @param ids
	 */
	@Override
	public void deleteAll(List<ID> ids) {
		ids.forEach(id -> {
			baseRepository.deleteById(id);
		});
	}

	public String updateStatus(StatusUpdateDto statusUpdateDto) {
		baseRepository.updateEntity(statusUpdateDto.getId(), statusUpdateDto.getStatusCode());
		return "Status updated successfully";
	}

	public String getMessage(String code, String[] args) {
		return messageSource.getMessage(code, args);
	}

	public String getMessage(String code) throws NoSuchMessageException {
		return messageSource.getMessage(code);
	}


	private String getUserAgent() {
	    RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
	    if (attribs != null) {
	        HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();
	        return request.getHeader(HttpHeaders.USER_AGENT);
	    }
	    return null;
	}

}
