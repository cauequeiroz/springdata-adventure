package main;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.service.CompanyCrudService;
import main.service.EmployeeCrudService;
import main.service.RoleCrudService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final RoleCrudService roleCrudService;
	private final CompanyCrudService companyCrudService;
	private final EmployeeCrudService employeeCrudService;
	private boolean running = true;
	
	public Application(RoleCrudService roleCrudService, CompanyCrudService companyCrudService, EmployeeCrudService employeeCrudService) {
		this.roleCrudService = roleCrudService;
		this.companyCrudService = companyCrudService;
		this.employeeCrudService = employeeCrudService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(running) {
			System.out.println("Choose your action:");
			System.out.println("1 - Role");
			System.out.println("2 - Company");
			System.out.println("3 - Employee");
			System.out.println("0 - Exit");
			
			int action = scanner.nextInt();
			
			switch(action) {
				case 1:
					roleCrudService.init(scanner);
					break;
				case 2:
					companyCrudService.init(scanner);
					break;
				case 3:
					employeeCrudService.init(scanner);
					break;
				default:
					running = false;
			}
		}
		
		
	}

}
