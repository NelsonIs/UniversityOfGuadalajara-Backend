package com.udg.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Major {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long majorId;
	@NotNull
	@NotEmpty
	@Size(max = 50)
	private String name;
	@NotNull
	@Min(value = 1, message = "The minium number of semesters should be 1")
	private int numOfSemesters;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private AreasOfMajors area;
}