package com.buland.graphql.netflixdgs.springboot.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.buland.graphqldgs.generated.types.EmployeeInput;
import com.buland.graphql.netflixdgs.springboot.entities.Employee;
import com.buland.graphql.netflixdgs.springboot.entities.Department;
import com.buland.graphql.netflixdgs.springboot.entities.Organization;
import com.buland.graphql.netflixdgs.springboot.repositories.EmployeeRepository;
import com.buland.graphql.netflixdgs.springboot.repositories.OrganizationRepository;
import com.buland.graphql.netflixdgs.springboot.repositories.DepartmentRepository;

@DgsComponent
public class EmployeeMutation {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    OrganizationRepository organizationRepository;

    EmployeeMutation(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    @DgsData(parentType = "Mutation", field = "newEmployee")
    public Employee addEmployee(@InputArgument("employee") EmployeeInput employeeInput) {
        Department department = departmentRepository.findById(employeeInput.getDepartmentId()).orElseThrow();
        Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).orElseThrow();
        return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
                employeeInput.getPosition(), employeeInput.getAge(), employeeInput.getSalary(),
                department, organization));
    }

}
