package main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
