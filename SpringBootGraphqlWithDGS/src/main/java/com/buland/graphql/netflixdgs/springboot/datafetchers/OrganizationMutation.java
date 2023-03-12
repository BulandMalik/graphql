package com.buland.graphql.netflixdgs.springboot.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.buland.graphqldgs.generated.types.OrganizationInput;
import com.buland.graphql.netflixdgs.springboot.entities.Organization;
import com.buland.graphql.netflixdgs.springboot.repositories.OrganizationRepository;

@DgsComponent
public class OrganizationMutation {

    OrganizationRepository repository;

    OrganizationMutation(OrganizationRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "Mutation", field = "newOrganization")
    public Organization newOrganization(@InputArgument("organization") OrganizationInput organizationInput) {
        return repository.save(new Organization(null, organizationInput.getName(), null, null));
    }

}
