package br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mapper.mocks;

import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDto mockDto() {
        return mockDto(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDto> mockDtoList() {
        List<PersonDto> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDto(i));
        }
        return persons;
    }

    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setPersonId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonDto mockDto(Integer number) {
        PersonDto person = new PersonDto();
        person.setAddress("Address Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setPersonId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}