package com.mathesucordeiro.personapi.service;

import com.mathesucordeiro.personapi.entity.Person;
import com.mathesucordeiro.personapi.repository.PersonRepository;
import com.mathesucordeiro.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonUtils person = new  PersonUtils();
        Person expectedSavedPerson = person.createFakeEntity();
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
    }
}

