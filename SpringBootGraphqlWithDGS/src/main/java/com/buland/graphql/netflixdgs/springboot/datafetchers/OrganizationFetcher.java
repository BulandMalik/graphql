package com.buland.graphql.netflixdgs.springboot.datafetchers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import com.buland.graphql.netflixdgs.springboot.entities.Organization;
import com.buland.graphql.netflixdgs.springboot.repositories.OrganizationRepository;


@DgsComponent
public class OrganizationFetcher {

    private OrganizationRepository repository;

    OrganizationFetcher(OrganizationRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "Query", field = "organizations")
    public Iterable<Organization> findAll() {
        return repository.findAll();
    }

    @DgsData(parentType = "Query", field = "organization")
    public Organization findById(@InputArgument("id") Integer id) {
        return repository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }
}
