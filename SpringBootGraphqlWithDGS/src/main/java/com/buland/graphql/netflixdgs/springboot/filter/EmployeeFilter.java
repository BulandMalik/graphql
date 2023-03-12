package com.buland.graphql.netflixdgs.springboot.filter;

//import com.buland.graphqldgs.generated.types.FilterField;
import lombok.Data;

@Data
public class EmployeeFilter {
	private FilterField salary;
	private FilterField age;
	private FilterField position;
}
