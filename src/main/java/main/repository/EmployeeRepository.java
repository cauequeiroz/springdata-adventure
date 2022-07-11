package main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
