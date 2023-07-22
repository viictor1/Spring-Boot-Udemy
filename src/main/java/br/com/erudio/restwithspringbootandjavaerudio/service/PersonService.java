package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.mapper.Mapper;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import br.com.erudio.restwithspringbootandjavaerudio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonDto findById(Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));
        return Mapper.parseObject(person, PersonDto.class);

    }
    public List<PersonDto> findAll(){
        return Mapper.parseListObjects(personRepository.findAll(), PersonDto.class);
    }

    public PersonDto create(PersonDto person){
        var entity = Mapper.parseObject(person, Person.class);
        personRepository.save(entity);
        return Mapper.parseObject(entity, PersonDto.class);
    }

    public PersonDto update(PersonDto person){
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(personRepository.save(entity), PersonDto.class);
    }

    public void delete(Long id){
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        personRepository.delete(entity);
    }

}
