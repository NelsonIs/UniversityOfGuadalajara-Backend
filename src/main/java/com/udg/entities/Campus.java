package com.udg.entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Campus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long campusId;
	@NotNull
	@NotEmpty
	@Size(max = 100)
	private String name;
	@NotNull
	@NotEmpty
	@Size(max = 10)
	private String abbreviation;
	@NotNull
	@NotEmpty
	@Size(max = 300)
	private String address;
}