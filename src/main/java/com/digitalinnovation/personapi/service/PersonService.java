package com.digitalinnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.mapper.PersonMapper;
import com.digitalinnovation.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public PersonService(PersonRepository repository) {
		super();
		this.repository = repository;
	}
	
	public MessageResponseDTO insert(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = repository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message(
						"Created person with ID " + savedPerson.getId())
				.build();
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = repository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO).collect(Collectors.toList());
	}
}
