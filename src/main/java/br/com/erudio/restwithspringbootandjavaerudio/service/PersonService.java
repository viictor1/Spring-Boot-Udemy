package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.controller.PersonController;
import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.dto.v2.dto.PersonDtoV2;
import br.com.erudio.restwithspringbootandjavaerudio.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.Mapper;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.custom.PersonMapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import br.com.erudio.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonDto findById(Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));
        PersonDto dto =  Mapper.parseObject(person, PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<PersonDto> findAll(){

        var persons =  Mapper.parseListObjects(personRepository.findAll(), PersonDto.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class)
                        .findById(p.getPersonId())).withSelfRel()));
        return persons;
    }

    public PersonDto create(PersonDto person){
        var entity = Mapper.parseObject(person, Person.class);
        entity = personRepository.save(entity);
        PersonDto dto =  Mapper.parseObject(entity, PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getPersonId())).withSelfRel());
        return dto;
    }

    public PersonDtoV2 createV2(PersonDtoV2 person){
        var entity = personMapper.convertDtoV2ToEntity(person);
        personRepository.save(entity);
        return personMapper.convertEntityToDtoV2(entity);
    }

    public PersonDto update(PersonDto person){
        Person entity = personRepository.findById(person.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDto dto =  Mapper.parseObject(entity, PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getPersonId())).withSelfRel());
        return dto;
    }

    public void delete(Long id){
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        personRepository.delete(entity);
    }

}
