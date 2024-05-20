package com.js.ems.service;

import java.util.List;
import java.util.Optional;

import com.js.ems.entity.Employee;

public interface EmployeeService {

	/**
	 * Retrieves a sorted list of employees based on the order specified.
	 *
	 * @param order the sort order (asc or desc)
	 * @return a sorted list of employees
	 */
	List<Employee> sortedList(String order);

	/**
	 * Saves an employee to the repository.
	 *
	 * @param employee the employee to be saved
	 */
	void save(Employee employee);

	/**
	 * Retrieves an employee by their ID.
	 *
	 * @param id the ID of the employee
	 * @return an Optional containing the employee if found, or empty if not found
	 */

	Optional<Employee> getEmployee(int id);

	/**
	 * Deletes an employee by their ID.
	 *
	 * @param id the ID of the employee to be deleted
	 */
	void deleteEmployee(int id);

	/**
	 * Searches for employees by their first name.
	 *
	 * @param firstName the first name to search for
	 * @return a list of employees matching the search criteria
	 */
	List<Employee> search(String firstName);

	/**
	 * Retrieves a list of all employees.
	 *
	 * @return a list of all employees
	 */
	List<Employee> list();

}