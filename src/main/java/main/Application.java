package main;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.service.RoleCrudService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final RoleCrudService roleCrudService;
	private boolean running = true;
	
	public Application(RoleCrudService roleCrudService) {
		this.roleCrudService = roleCrudService;
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
			System.out.println("0 - Exit");
			
			int action = scanner.nextInt();
			
			switch(action) {
				case 1:
					roleCrudService.init(scanner);
					break;
				default:
					running = false;
			}
		}
		
		
	}

}
