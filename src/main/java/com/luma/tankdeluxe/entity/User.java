package com.luma.tankdeluxe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Type(type="uuid-char")
	@Column(unique = true)
	private UUID uuid;
	
	@Column(unique = true)
	private String login;
	
	private String password;
	
	private String role;
	
}