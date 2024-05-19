package com.js.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.js.ems.entity.Employee;
import com.js.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> sortedList(String order) {
		
		Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);		
	}

	public Optional<Employee> getEmployee(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	public List<Employee> search(String firstName) {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByfirstName(firstName);
	}

	public List<Employee> list() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

}
