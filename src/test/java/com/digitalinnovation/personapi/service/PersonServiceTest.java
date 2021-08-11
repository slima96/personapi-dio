package com.digitalinnovation.personapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import com.digitalinnovation.personapi.utils.PersonUtils;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository repository;
	
	@InjectMocks
	private PersonService service;
	
	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {
		PersonDTO personDTO = PersonUtils.createFakeDTO();
		Person expectedSavedPerson = PersonUtils.createFakeEntity();
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(expectedSavedPerson);
		
		MessageResponseDTO expectedSucessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		
		MessageResponseDTO sucessMessage = service.insert(personDTO);
		
		Assertions.assertEquals(expectedSucessMessage, sucessMessage);
		
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + id)
				.build();
	}
}
