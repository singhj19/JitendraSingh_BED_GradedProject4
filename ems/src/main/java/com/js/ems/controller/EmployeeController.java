package com.js.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.ems.entity.Employee;
import com.js.ems.exception.EmployeeNotFoundException;
import com.js.ems.exception.EmployeeServiceException;
import com.js.ems.exception.ErrorResponse;
import com.js.ems.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Retrieves the list of all employees.
	 *
	 * @return a list of employees
	 */

	@GetMapping("/list")
	public ResponseEntity<Object> listEmployees() {
		try {
			List<Employee> employees = employeeService.list();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (EmployeeServiceException e) {
			ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves a sorted list of employees.
	 *
	 * @param order the sort order (asc or desc)
	 * @return a sorted list of employees
	 */
	@GetMapping("/sort")
	public ResponseEntity<List<Employee>> listSortedEmployees(@RequestParam(defaultValue = "desc") String order) {
		List<Employee> employees = employeeService.sortedList(order);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	/**
	 * Saves a new employee.
	 *
	 * @param employee the employee to be saved
	 * @return a response entity with HTTP status
	 */
	@PostMapping("/save")
	public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Retrieves an employee by ID.
	 *
	 * @param id the ID of the employee
	 * @return the employee with the given ID
	 */
	@GetMapping("/view")
	public ResponseEntity<Employee> getEmployee(@RequestParam("id") int id) {
		try {
			Optional<Employee> employee = employeeService.getEmployee(id);
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} catch (EmployeeNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes an employee by ID.
	 *
	 * @param id the ID of the employee to be deleted
	 * @return a response entity with HTTP status
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteEmployee(@RequestParam("id") int id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Updates an existing employee.
	 *
	 * @param employee the employee to be updated
	 * @return a response entity with HTTP status
	 */
	@PutMapping("/update")
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Searches for employees by first name.
	 *
	 * @param firstName the first name to search for
	 * @return a list of employees matching the search criteria
	 */
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> searchEmployee(@RequestParam("firstName") String firstName) {
		List<Employee> employees = employeeService.search(firstName);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
}
