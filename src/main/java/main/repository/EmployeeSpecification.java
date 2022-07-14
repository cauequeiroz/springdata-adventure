package main.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import main.entity.Employee;

public class EmployeeSpecification {
	public static Specification<Employee> nameLike(String name) {
		return (root, query, builder) ->
			builder.like(root.get("name"), "%" + name + "%");
	}
	
	public static Specification<Employee> cpfEquals(String cpf) {
		return (root, query, builder) ->
			builder.equal(root.get("cpf"), cpf);
	}
	
	public static Specification<Employee> salaryGreaterThan(BigDecimal salary) {
		return (root, query, builder) ->
			builder.greaterThan(root.get("salary"), salary);
	}
}
