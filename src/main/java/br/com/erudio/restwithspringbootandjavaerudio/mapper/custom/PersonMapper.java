package br.com.erudio.restwithspringbootandjavaerudio.mapper.custom;

import br.com.erudio.restwithspringbootandjavaerudio.dto.v2.dto.PersonDtoV2;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonDtoV2 convertEntityToDtoV2(Person entity){
        PersonDtoV2 dto = new PersonDtoV2();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setBirthday(new Date());
        return dto;
    }
    public Person convertDtoV2ToEntity(PersonDtoV2 dto){
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return entity;
    }

}
