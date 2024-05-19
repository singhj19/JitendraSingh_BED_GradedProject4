package com.js.ems.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.js.ems.entity.Employee;
import com.js.ems.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employservice;
	
	@GetMapping("/list")
	public List<Employee> listEmployees() {
		
		List<Employee> employees = employservice.list();
		return employees;
	}
	
	@GetMapping("/sort")
	public List<Employee> listSortedEmployees(@RequestParam(defaultValue = "desc") String order) {
		
		List<Employee> employees = employservice.sortedList(order);
		return employees;
	}
	
	@PostMapping("/save")
	public void saveEmployee(@ModelAttribute("employee") Employee employee ) {
		employservice.save(employee);
	}
	
	@PostMapping("/view")
	public Optional<Employee> getEmployee(@RequestParam("id") int id) {
		return employservice.getEmployee(id);				
	}
	
	@PostMapping("/delete")
	public void deleteEmployee(@RequestParam("id") int id) {
		employservice.deleteEmployee(id);
	}
	
	@PostMapping("/update")
	public void updateEmployee(@ModelAttribute("employee") Employee employee ) {
		employservice.save(employee);
	}
	
	@PostMapping("/search")
	public List<Employee> searchEmployee(@RequestParam("firstName") String firstName) {
		return employservice.search(firstName);
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView handleAccessDeniedError(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("authorization-error-403");
		return model;
	}
	
	

}
