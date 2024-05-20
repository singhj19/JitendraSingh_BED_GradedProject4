package com.js.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.js.ems.entity.Employee;

/**
 * Repository interface for Employee entity.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	/**
	 * Retrieves all employees sorted by first name in descending order.
	 *
	 * @return a list of employees sorted by first name in descending order
	 */
	List<Employee> findAllByOrderByFirstNameDesc();

	/**
	 * Searches for employees whose first name contains the specified string,
	 * case-insensitive.
	 *
	 * @param firstName the substring to search for in the first name
	 * @return a list of employees whose first name contains the specified substring
	 */
	@Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
	List<Employee> findAllByFirstNameContainingIgnoreCase(@Param("firstName") String firstName);
}
