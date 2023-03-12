package com.buland.graphql.netflixdgs.springboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	private String name;
	@OneToMany(mappedBy = "department")
	private Set<Employee> employees;
	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;
}
