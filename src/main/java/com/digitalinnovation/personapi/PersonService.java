package com.digitalinnovation.personapi;

import org.springframework.stereotype.Service;

import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;

	public PersonService(PersonRepository repository) {
		super();
		this.repository = repository;
	}
	
	public MessageResponseDTO insert(Person person) {
		Person savedPerson = repository.save(person);
		return MessageResponseDTO
				.builder()
				.message(
						"Created person with ID " + savedPerson.getId())
				.build();
	}
}
