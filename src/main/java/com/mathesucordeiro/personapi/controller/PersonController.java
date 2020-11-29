package com.mathesucordeiro.personapi.controller;

import com.mathesucordeiro.personapi.dto.PersonDTO;
import com.mathesucordeiro.personapi.entity.Person;
import com.mathesucordeiro.personapi.exception.PersonNotFoundException;
import com.mathesucordeiro.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.getById(id);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid  PersonDTO personDTO) {
        try {

            return new ResponseEntity(personService.save(personDTO) , HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        try {
            personService.update(id, personDTO);
            return ResponseEntity.ok( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{/id}")
    public ResponseEntity delete(@PathVariable Long id) throws PersonNotFoundException {
        try {
            personService.delete(id);
            return ResponseEntity.ok( HttpStatus.NO_CONTENT );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
