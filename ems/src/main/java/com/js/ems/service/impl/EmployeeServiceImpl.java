package com.js.ems.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.ems.entity.Employee;
import com.js.ems.exception.EmployeeNotFoundException;
import com.js.ems.exception.EmployeeServiceException;
import com.js.ems.repository.EmployeeRepository;
import com.js.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * Retrieves a sorted list of employees based on the order specified.
	 *
	 * @param order the sort order (asc or desc)
	 * @return a sorted list of employees
	 */
	@Override
	public List<Employee> sortedList(String order) {
		try {
			Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
			return employeeRepository.findAll(Sort.by(direction, "firstName"));
		} catch (Exception e) {
			logger.error("Error occurred while fetching sorted employee list", e);
			throw new EmployeeServiceException("Unable to fetch sorted employee list", e);
		}
	}

	/**
	 * Saves an employee to the repository.
	 *
	 * @param employee the employee to be saved
	 */
	@Override
	public void save(Employee employee) {
		try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			logger.error("Error occurred while saving employee", e);
			throw new EmployeeServiceException("Unable to save employee", e);
		}
	}

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id the ID of the employee
	 * @return an Optional containing the employee if found, or empty if not found
	 */
	@Override
	public Optional<Employee> getEmployee(int id) {
		Optional<Employee> employee;
		try {
			employee = employeeRepository.findById(id);
			if (employee.isEmpty()) {
				throw new EmployeeNotFoundException("Employee with id " + id + " not found");
			}

		} catch (EmployeeNotFoundException e) {
			throw e; // rethrowing custom exception
		} catch (Exception e) {
			throw new EmployeeServiceException("Error retrieving employee with id " + id, e);
		}
		return employee;
	}

	/**
	 * Deletes an employee by their ID.
	 *
	 * @param id the ID of the employee to be deleted
	 */
	@Override
	public void deleteEmployee(int id) {
		try {
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("Error occurred while deleting employee", e);
			throw new EmployeeServiceException("Unable to delete employee", e);
		}
	}

	/**
	 * Searches for employees by their first name.
	 *
	 * @param firstName the first name to search for
	 * @return a list of employees matching the search criteria
	 */
	@Override
	public List<Employee> search(String firstName) {
		try {
			return employeeRepository.findAllByFirstNameContainingIgnoreCase(firstName);
		} catch (Exception e) {
			logger.error("Error occurred while searching for employees", e);
			throw new EmployeeServiceException("Unable to search for employees", e);
		}
	}

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return a list of all employees
	 */
	@Override
	public List<Employee> list() {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			logger.error("Error occurred while fetching employee list", e);
			throw new EmployeeServiceException("Unable to fetch employee list", e);
		}
	}
}
