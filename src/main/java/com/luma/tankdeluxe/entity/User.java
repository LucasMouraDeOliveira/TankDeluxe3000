package com.luma.tankdeluxe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Type(type="uuid-char")
	@Column(unique = true)
	@JsonProperty("id")
	private UUID uuid;
	
	@Column(unique = true)
	private String login;
	
	@JsonIgnore
	private String password;
	
	private String role;
	
}