package br.com.erudio.restwithspringbootandjavaerudio.controller;

import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.dto.v2.dto.PersonDtoV2;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.custom.PersonMapper;
import br.com.erudio.restwithspringbootandjavaerudio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto findById(@PathVariable(value = "id") Long id){
        return personService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> findAll(){
        return personService.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto create(@RequestBody PersonDto person){
        return personService.create(person);
    }

    @PostMapping(value= "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDtoV2 createV2(@RequestBody PersonDtoV2 person){
        return personService.createV2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto update(@RequestBody PersonDto person){
        return personService.update(person);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
