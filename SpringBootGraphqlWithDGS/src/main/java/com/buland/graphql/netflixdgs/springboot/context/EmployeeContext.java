package com.buland.graphql.netflixdgs.springboot.context;

import com.buland.graphql.netflixdgs.springboot.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeContext {
    private List<Employee> employees = new ArrayList<Employee>();
}
