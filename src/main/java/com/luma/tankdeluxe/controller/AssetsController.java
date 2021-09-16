package com.luma.tankdeluxe.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assets")
public class AssetsController {

	@Value("${app.assets.models.location}")
	private String modelsLocation;
	
	@GetMapping("models")
	public ResponseEntity<List<String>> listModels() {
		
		List<String> modelSet = Arrays.asList(new File(this.modelsLocation).list());
		
		return new ResponseEntity<>(modelSet, HttpStatus.OK);
	}
}
