package br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mockito.services;

import br.com.erudio.restwithspringbootandjavaerudio.UnitTests.mapper.mocks.MockPerson;
import br.com.erudio.restwithspringbootandjavaerudio.dto.PersonDto;
import br.com.erudio.restwithspringbootandjavaerudio.exception.RequiredObjectIsNullException;
import br.com.erudio.restwithspringbootandjavaerudio.model.Person;
import br.com.erudio.restwithspringbootandjavaerudio.repository.PersonRepository;
import br.com.erudio.restwithspringbootandjavaerudio.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setPersonId(1l);
        
        when(personRepository.findById(1l)).thenReturn(Optional.of(person));

        var result = personService.findById(1l);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setPersonId(1l);

        PersonDto dto = input.mockDto(1);
        dto.setPersonId(1l);

        when(personRepository.save(person)).thenReturn(persisted);

        var result = personService.create(dto);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.create(null));
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        person.setPersonId(1l);
        Person persisted = person;

        PersonDto dto = input.mockDto(1);
        dto.setPersonId(1l);

        when(personRepository.findById(1l)).thenReturn(Optional.of(person));
        lenient().when(personRepository.save(person)).thenReturn(persisted);

        var result = personService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getPersonId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void updateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> personService.update(null));
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void delete() {
        List<Person> personList = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(personList);

        var result = personService.findAll();

        var personOne = result.get(1);

        assertNotNull(result);
        assertEquals(14, result.size());

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());

        var personFive = result.get(5);

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test5", personFive.getFirstName());
        assertEquals("Last Name Test5", personFive.getLastName());
        assertEquals("Address Test5", personFive.getAddress());
        assertEquals("Female", personFive.getGender());

        var personTen = result.get(10);

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test10", personTen.getFirstName());
        assertEquals("Last Name Test10", personTen.getLastName());
        assertEquals("Address Test10", personTen.getAddress());
        assertEquals("Male", personTen.getGender());
    }
}