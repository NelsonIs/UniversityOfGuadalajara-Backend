package com.udg.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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
public class Users {
	@Id
	@NotNull
	@NotEmpty
	private String username;
	@NotNull
	@NotEmpty
	@Size(min = 8)
	private String password;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private TypeUsers role;
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String lastName;
}