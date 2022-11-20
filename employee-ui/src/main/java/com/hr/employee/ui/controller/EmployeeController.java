package com.hr.employee.ui.controller;

import com.hr.employee.ui.client.EmployeeClientRestTemplate;
import com.hr.employee.ui.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeClientRestTemplate employeeClient;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeClient.findAll());
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		EmployeeDto employee = new EmployeeDto();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") EmployeeDto employee) {

		employeeClient.insert(employee);
		return "redirect:/";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("employee") EmployeeDto employee) {
		log.info("update employee with id ==>> {}", employee.getId());
		employeeClient.update(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get employee from the service
		EmployeeDto employee = employeeClient.findById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.employeeClient.deleteById(id);
		return "redirect:/";
	}
}
