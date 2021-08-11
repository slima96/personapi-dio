package com.digitalinnovation.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.exception.PersonNotFoundException;
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
		return createMessageResponse(
				savedPerson.getId(),
				"Created person with ID ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = repository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO).collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExist(id);
		
		return personMapper.toDTO(person);
	}
	
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExist(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatedPerson = repository.save(personToUpdate);
		return createMessageResponse(
				updatedPerson.getId(),
				"Updated person with ID ");
	}

	public void deleteById(Long id) throws PersonNotFoundException {
		verifyIfExist(id);
		repository.deleteById(id);
	}
	
	private Person verifyIfExist(Long id) throws PersonNotFoundException {
		return repository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}

}
