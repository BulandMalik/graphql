package com.buland.graphql.netflixdgs.springboot.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import com.buland.graphqldgs.generated.types.DepartmentInput;
import com.buland.graphql.netflixdgs.springboot.entities.Department;
import com.buland.graphql.netflixdgs.springboot.entities.Organization;
import com.buland.graphql.netflixdgs.springboot.repositories.DepartmentRepository;
import com.buland.graphql.netflixdgs.springboot.repositories.OrganizationRepository;

@DgsComponent
public class DepartmentMutation {

    DepartmentRepository departmentRepository;
    OrganizationRepository organizationRepository;

    DepartmentMutation(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.organizationRepository = organizationRepository;
    }

    @DgsData(parentType = "Mutation", field = "newDepartment")
    public Department newDepartment(@InputArgument("department") DepartmentInput departmentInput) {
        Organization organization = organizationRepository.findById(departmentInput.getOrganizationId()).orElseThrow();
        return departmentRepository.save(new Department(null, departmentInput.getName(), null, organization));
    }

}
