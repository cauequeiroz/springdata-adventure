package main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
}
