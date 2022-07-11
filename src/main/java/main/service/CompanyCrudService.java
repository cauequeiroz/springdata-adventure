package main.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import main.entity.Company;
import main.repository.CompanyRepository;

@Service
public class CompanyCrudService {
	
	private final CompanyRepository companyRepository;
	private boolean running = false;
	
	public CompanyCrudService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	public void init(Scanner scanner) {
		running = true;
		
		while(running) {
			System.out.println("\n[Company] Choose your action:");
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
		System.out.print("Company name: ");
		String name = scanner.next();
		
		Company company = new Company();
		company.setName(name);
		
		companyRepository.save(company);	
		System.out.println("Saved.");
	}
	
	public void update(Scanner scanner) {
		System.out.print("Company id: ");
		int id = scanner.nextInt();
		System.out.print("Company name: ");
		String name = scanner.next();
		
		Company company = new Company();
		company.setId(id);
		company.setName(name);
		
		companyRepository.save(company);
		System.out.println("Updated.");
	}
	
	public void read() {
		companyRepository.findAll().forEach(System.out::println);
	}
	
	public void delete(Scanner scanner) {
		System.out.print("Company id: ");
		int id = scanner.nextInt();
		
		companyRepository.deleteById(id);
		System.out.println("Deleted.");
	}
}
