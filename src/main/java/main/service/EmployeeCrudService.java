package main.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import main.entity.Company;
import main.entity.Employee;
import main.repository.CompanyRepository;
import main.repository.EmployeeRepository;
import main.repository.RoleRepository;

@Service
public class EmployeeCrudService {
	
	private final EmployeeRepository employeeRepository;
	private final RoleRepository roleRepository;
	private final CompanyRepository companyRepository;
	private boolean running = false;
	
	public EmployeeCrudService(EmployeeRepository employeeRepository, RoleRepository roleRepository, CompanyRepository companyRepository) {
		this.employeeRepository = employeeRepository;
		this.roleRepository = roleRepository;
		this.companyRepository = companyRepository;
	}
	
	public void init(Scanner scanner) {
		running = true;
		
		while(running) {
			System.out.println("\n[Employee] Choose your action:");
			System.out.println("1 - Create");
			System.out.println("2 - Read all");
			System.out.println("3 - Update");
			System.out.println("4 - Delete");
			System.out.println("0 - Exit");
			
			int action = scanner.nextInt();
			
			switch(action) {
				case 1:
					create(scanner);
					break;
				case 2:
					read();
					break;
				case 3:
					update(scanner);
					break;
				case 4:
					delete(scanner);
					break;
				default:
					running = false;
			}
		}
	}
	
	public void create(Scanner scanner) {
		System.out.print("Employee name: ");
		String name = scanner.next();
		System.out.print("Employee cpf: ");
		String cpf = scanner.next();
		System.out.print("Employee salary: ");
		Double salary = scanner.nextDouble();		
		System.out.print("Employee role id: ");
		int roleId = scanner.nextInt();
		LocalDate startDate = LocalDate.now();
		
		
		boolean choosingCompanyId = true;
		List<Company> companies = new ArrayList<>();
		
		while(choosingCompanyId) {
			System.out.print("Employee company id (0 to exit): ");
			int companyId = scanner.nextInt();
			
			if (companyId == 0) {
				choosingCompanyId = false;
				continue;
			}
			
			Company company = companyRepository.findById(companyId).get();
			companies.add(company);
		}
				
		Employee employee = new Employee();
		employee.setName(name);
		employee.setCpf(cpf);
		employee.setSalary(new BigDecimal(salary));
		employee.setStartDate(startDate);
		employee.setRole(roleRepository.findById(roleId).get());
		employee.setCompanies(companies);
		
		employeeRepository.save(employee);	
		System.out.println("Saved.");
	}
	
	public void update(Scanner scanner) {
		System.out.print("Employee id: ");
		int id = scanner.nextInt();
		System.out.print("Employee name: ");
		String name = scanner.next();
		System.out.print("Employee cpf: ");
		String cpf = scanner.next();
		System.out.print("Employee salary: ");
		Double salary = scanner.nextDouble();
		LocalDate startDate = LocalDate.now();
		
		Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setCpf(cpf);
		employee.setSalary(new BigDecimal(salary));
		employee.setStartDate(startDate);
		
		
		employeeRepository.save(employee);	
		System.out.println("Updated.");
	}
	
	public void read() {
		employeeRepository.findAll().forEach(System.out::println);
	}
	
	public void delete(Scanner scanner) {
		System.out.print("Employee id: ");
		int id = scanner.nextInt();
		
		employeeRepository.deleteById(id);
		System.out.println("Deleted.");
	}
}
