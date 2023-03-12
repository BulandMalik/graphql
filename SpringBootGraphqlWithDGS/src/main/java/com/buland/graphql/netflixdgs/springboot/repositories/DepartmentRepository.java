package com.buland.graphql.netflixdgs.springboot.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import com.buland.graphql.netflixdgs.springboot.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>,
		JpaSpecificationExecutor<Department> {

}
