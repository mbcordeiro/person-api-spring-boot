package com.mathesucordeiro.personapi.service;

import com.mathesucordeiro.personapi.dto.PersonDTO;
import com.mathesucordeiro.personapi.entity.Person;
import com.mathesucordeiro.personapi.exception.PersonNotFoundException;
import com.mathesucordeiro.personapi.mapper.PersonMapper;
import com.mathesucordeiro.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    public List<PersonDTO> getAll() {
        List<Person> person = personRepository.findAll();
        return person.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO getById(Long id) throws PersonNotFoundException {
        Person person = verifyIdExists(id);
        return personMapper.toDTO(person);
    }

    public PersonDTO save(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        personRepository.save(person);
        return personMapper.toDTO(person);
    }

    public PersonDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIdExists(id);

        Person person = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(person);

        return personMapper.toDTO(updatedPerson);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIdExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIdExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
