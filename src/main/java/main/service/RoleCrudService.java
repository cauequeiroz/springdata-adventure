package main.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import main.entity.Role;
import main.repository.RoleRepository;

@Service
public class RoleCrudService {
	
	private final RoleRepository roleRepository;
	private boolean running = false;
	
	public RoleCrudService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public void init(Scanner scanner) {
		running = true;
		
		while(running) {
			System.out.println("[Role] Choose your action:");
			System.out.println("1 - Create role");
			System.out.println("2 - Update role");
			System.out.println("0 - Exit");
			
			int action = scanner.nextInt();
			
			switch(action) {
				case 1:
					create(scanner);
					break;
				case 2:
					update(scanner);
					break;
				default:
					running = false;
			}
		}
	}
	
	public void create(Scanner scanner) {
		System.out.print("Role name: ");
		String name = scanner.next();
		
		Role role = new Role();
		role.setName(name);
		
		roleRepository.save(role);	
		System.out.println("Saved.\n");
	}
	
	public void update(Scanner scanner) {
		System.out.print("Role id: ");
		int id = scanner.nextInt();
		System.out.print("Role name: ");
		String name = scanner.next();
		
		Role role = new Role();
		role.setId(id);
		role.setName(name);
		
		roleRepository.save(role);
		System.out.println("Updated.\n");
	}
}
