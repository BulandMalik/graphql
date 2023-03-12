package com.buland.graphql.netflixdgs.springboot.utils;

import com.buland.graphqldgs.generated.types.Department;
import com.buland.graphqldgs.generated.types.Employee;
import com.buland.graphqldgs.generated.types.Organization;

import java.util.ArrayList;
import java.util.List;

public class Utils {
/*
    public static List<Organization> convertOrganizationEntityToDTO(List<com.buland.graphql.netflixdgs.springboot.entities.Organization> orgEntities) {
        List<Organization> organizations = new ArrayList<Organization>();
        orgEntities.stream().forEach( orgEntity -> {
            Organization org = Organization.newBuilder().build();
            org.setId(String.valueOf(orgEntity.getId()));
            org.setName(orgEntity.getName());
            org.setDepartments(convertDepartmentEntityToDTO(orgEntity.getDepartments()));
            organizations.add(org);
        });

        return organizations;
    }

    public static List<Organization> convertIterOrganizationEntityToDTO(
            Iterable<com.buland.graphql.netflixdgs.springboot.entities.Organization> orgEntities
    ) {
        List<Organization> organizations = new ArrayList<Organization>();

        orgEntities.forEach( orgEntity -> {
            Organization org = Organization.newBuilder().build();
            org.setId(String.valueOf(orgEntity.getId()));
            org.setName(orgEntity.getName());
            org.setDepartments(convertDepartmentEntityToDTO(orgEntity.getDepartments()));
            organizations.add(org);
        });

        return organizations;
    }

    public static List<Department> convertDepartmentEntityToDTO(List<com.buland.graphql.netflixdgs.springboot.entities.Department> depEntities) {
        List<Department> departments = new ArrayList<Department>();
        depEntities.stream().forEach( depEntity -> {
            Department department = Department.newBuilder().build();
            department.setId(String.valueOf(depEntity.getId()));
            department.setName(depEntity.getName());
            List<Employee> emps = convertEmployeeEntityToDTO(depEntity.getEmployees());
            department.setEmployees(emps);
            List<Organization> orgs = convertOrganizationEntityToDTO(List.of(depEntity.getOrganization()));
            department.setOrganization(orgs.size()>0 ? orgs.get(0) : null);
            departments.add(department);
        });

        return departments;
    }

    public static List<Employee> convertEmployeeEntityToDTO(List<com.buland.graphql.netflixdgs.springboot.entities.Employee> empEntities) {
        List<Employee> employees = new ArrayList<Employee>();
        empEntities.stream().forEach( empEntity -> {
            Employee employee = Employee.newBuilder().build();
            employee.setId(String.valueOf(empEntity.getId()));
            employee.setAge(empEntity.getAge());
            employee.setFirstName(empEntity.getFirstName());
            employee.setLastName(empEntity.getLastName());
            employee.setPosition(empEntity.getPosition());
            employee.setSalary(empEntity.getSalary());
            List<Organization> orgs = convertOrganizationEntityToDTO(List.of(empEntity.getOrganization()));
            employee.setOrganization(orgs.size()>0 ? orgs.get(0) : null);
            List<Department> deps = convertDepartmentEntityToDTO(List.of(empEntity.getDepartment()));
            employee.setDepartment(deps.size()>0 ? deps.get(0) : null);
            employees.add(employee);
        });

        return employees;
    }

 */
}
