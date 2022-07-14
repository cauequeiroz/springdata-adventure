package main.service;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import main.entity.Employee;
import main.entity.Role;
import main.repository.EmployeeRepository;
import main.repository.EmployeeSpecification;
import main.repository.RoleRepository;

@Service
public class PlaygroundService {
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	
	public PlaygroundService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
		this.employeeRepository = employeeRepository;
		this.roleRepository =roleRepository;
	}
	
	public void init(Scanner scanner) {
		System.out.println("\nFind employee by name: asdf");
		employeeRepository.findByName("asdf").forEach(System.out::println);		
		
		System.out.println("\nFind employee by salary greater than: 250.0");
		employeeRepository.findBySalaryBiggerThan(new BigDecimal(250.0)).forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary()));	
		
		System.out.println("\nFind role by name size: 8");
		roleRepository.findByNameSize(8).forEach(System.out::println);
		
		System.out.println("\nFind role with pagination:");
		Page<Role> roles = roleRepository.findAll(PageRequest.of(0, 3));
		roles.forEach(System.out::println);
		System.out.println((roles.getNumber() + 1) + "/" + roles.getTotalPages() + " - Total: " + roles.getTotalElements());
		
		roles = roleRepository.findAll(PageRequest.of(1, 3, Sort.by(Direction.ASC, "name")));
		roles.forEach(System.out::println);
		System.out.println((roles.getNumber() + 1) + "/" + roles.getTotalPages() + " - Total: " + roles.getTotalElements());
		
		roles = roleRepository.findAll(PageRequest.of(2, 3));
		roles.forEach(System.out::println);
		System.out.println((roles.getNumber() + 1) + "/" + roles.getTotalPages() + " - Total: " + roles.getTotalElements());
		
		System.out.println("\n==========\n");
		
		System.out.println("Dynamic employee search:");
		
		String name = "Je";
		String cpf = null;
		BigDecimal salary = new BigDecimal("200");
		
		Specification<Employee> condition = Specification.where(null);
		
		if (name != null) {
			condition = condition.and(EmployeeSpecification.nameLike(name));
		}
		
		if (cpf != null) {
			condition = condition.and(EmployeeSpecification.cpfEquals(cpf));
		}
		
		if (salary != null) {
			condition = condition.and(EmployeeSpecification.salaryGreaterThan(salary));
		}
		
		employeeRepository.findAll(condition).forEach(System.out::println);
	}
}
