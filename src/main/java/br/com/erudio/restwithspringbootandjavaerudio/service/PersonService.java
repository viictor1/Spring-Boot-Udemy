package br.com.erudio.restwithspringbootandjavaerudio.service;

import br.com.erudio.restwithspringbootandjavaerudio.exception.ResourceNotFoundException;
import br.com.erudio.restwithspringbootandjavaerudio.model.PersonDto;
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
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));
    }
    public List<PersonDto> findAll(){
        return personRepository.findAll();
    }

    public PersonDto create(PersonDto person){
        return personRepository.save(person);
    }

    public PersonDto update(PersonDto person){
        PersonDto entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id){
        PersonDto entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Records not found for this ID"));

        personRepository.delete(entity);
    }

}
