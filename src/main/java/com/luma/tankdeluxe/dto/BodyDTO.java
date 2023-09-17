package com.luma.tankdeluxe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BodyDTO {

	private int x;
	private int y;
	
	@JsonInclude(Include.NON_NULL)
	private Double angle;
	
}
