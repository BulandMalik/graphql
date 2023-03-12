package com.buland.graphql.netflixdgs.springboot.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.data.jpa.domain.Specification;
import com.buland.graphql.netflixdgs.springboot.context.EmployeeContext;
import com.buland.graphql.netflixdgs.springboot.entities.Department;
import com.buland.graphql.netflixdgs.springboot.entities.Employee;
import com.buland.graphql.netflixdgs.springboot.entities.Organization;
import com.buland.graphql.netflixdgs.springboot.repositories.DepartmentRepository;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@DgsComponent
public class DepartmentFetcher {

    private DepartmentRepository repository;

    DepartmentFetcher(DepartmentRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "Query", field = "departments")
    public Iterable<Department> findAll(DataFetchingEnvironment environment) {
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        if (s.contains("employees") && !s.contains("organization"))
            return repository.findAll(fetchEmployees());
        else if (!s.contains("employees") && s.contains("organization"))
            return repository.findAll(fetchOrganization());
        else if (s.contains("employees") && s.contains("organization"))
            return repository.findAll(fetchEmployees().and(fetchOrganization()));
        else
            return repository.findAll();
    }

    @DgsData(parentType = "Query", field = "department")
    public Department findById(@InputArgument("id") Integer id, DataFetchingEnvironment environment) {
        Specification<Department> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        EmployeeContext employeeContext = DgsContext.getCustomContext(environment);
        Set<Employee> employees = null;
        if (selectionSet.contains("employees")) {
            if (employeeContext.getEmployees().size() == 0)
                spec = spec.and(fetchEmployees());
            else
                employees = employeeContext.getEmployees().stream()
                        .filter(emp -> emp.getDepartment().getId().equals(id))
                        .collect(Collectors.toSet());
        }
        if (selectionSet.contains("organization"))
            spec = spec.and(fetchOrganization());
        Department department = repository.findOne(spec).orElseThrow(DgsEntityNotFoundException::new);
        if (employees != null)
            department.setEmployees(employees);
        return department;
    }

    private Specification<Department> fetchOrganization() {
        return (root, query, builder) -> {
            Fetch<Department, Organization> f = root.fetch("organization", JoinType.LEFT);
            Join<Department, Organization> join = (Join<Department, Organization>) f;
            return join.getOn();
        };
    }

    private Specification<Department> fetchEmployees() {
        return (root, query, builder) -> {
            Fetch<Department, Employee> f = root.fetch("employees", JoinType.LEFT);
            Join<Department, Employee> join = (Join<Department, Employee>) f;
            return join.getOn();
        };
    }

    private Specification<Department> byId(Integer id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }
}
