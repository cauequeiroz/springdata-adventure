package main.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	List<Employee> findByName(String name);
	
	@Query("""
			SELECT e FROM Employee e
			WHERE e.salary > :salary
			""")
	List<Employee> findBySalaryBiggerThan(BigDecimal salary);
}
