package com.hr.employee.ui.client;

import com.hr.employee.ui.dto.EmployeeDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class EmployeeClientRestTemplate {

    private static final String EMPLOYEE_ENDPOINT_URL = "http://localhost:8081/v1/employee";
    private static RestTemplate restTemplate = new RestTemplate();


    public List<EmployeeDto> findAll() {
        ResponseEntity<List<EmployeeDto>> result = restTemplate.exchange(EMPLOYEE_ENDPOINT_URL, HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<EmployeeDto>>() {
        });
        return result.getBody();
    }

//    public ResponseEntity findAllPaging(Integer pageNum, Integer size, String sortableColumn) {
//        Map< String, String > params = new HashMap< String, String >();
//        params.put("pageNum", String.valueOf(pageNum));
//        params.put("size", String.valueOf(size));
//        params.put("sortableColumn", sortableColumn);
//        ResponseEntity<Page<EmployeeDto>> result = restTemplate.exchange(EMPLOYEE_ENDPOINT_URL, HttpMethod.GET, HttpEntity.EMPTY,
//                new ParameterizedTypeReference<List<EmployeeDto>>() {
//                }, params);
//        return null;
//    }

    public EmployeeDto findById(Long id) {
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", String.valueOf(id));
        RestTemplate restTemplate = new RestTemplate();
        EmployeeDto result = restTemplate.getForObject(EMPLOYEE_ENDPOINT_URL + "/{id}", EmployeeDto.class, params);
        return result;
    }

    public EmployeeDto insert(EmployeeDto dto) {
        EmployeeDto result = restTemplate.postForObject(EMPLOYEE_ENDPOINT_URL, dto, EmployeeDto.class);
        return result;
    }

    public void update(EmployeeDto dto) {
        restTemplate.put(EMPLOYEE_ENDPOINT_URL, dto);
    }

    public void deleteById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(EMPLOYEE_ENDPOINT_URL +"/?id=" +id);
    }
}
