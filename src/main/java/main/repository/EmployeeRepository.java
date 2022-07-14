package main.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Employee;
import main.entity.EmployeeSalaryDTO;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
	List<Employee> findByName(String name);
	
	@Query(value = """
			SELECT e.id, e.name, e.salary FROM employees e
			WHERE e.salary > :salary
			""", nativeQuery = true)
	List<EmployeeSalaryDTO> findBySalaryBiggerThan(BigDecimal salary);
	
	
}
