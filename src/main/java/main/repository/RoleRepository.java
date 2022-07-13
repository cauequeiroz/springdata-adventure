package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import main.entity.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
	
	@Query(value = "SELECT * FROM roles WHERE LENGTH(name) > :size", nativeQuery = true)
	List<Role> findByNameSize(int size);
}
