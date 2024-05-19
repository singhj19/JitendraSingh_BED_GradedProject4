package com.js.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.js.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
	List<Employee> findAllByOrderByFirstNameDesc();

	 @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
	List<Employee> findAllByfirstName(String firstName);

}
