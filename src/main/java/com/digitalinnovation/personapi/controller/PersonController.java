package com.digitalinnovation.personapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	private PersonService service;
	
	@Autowired
	public PersonController(PersonService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO insert(@RequestBody @Valid PersonDTO personDTO) {
		return service.insert(personDTO);
	}
}
