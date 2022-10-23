package com.example.people.service;

import com.example.people.dto.PersonDto;
import com.example.people.models.Person;
import com.example.people.repository.PersonRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void getPersonData() {
        when(personRepository.findById(1L)).thenReturn(returnPerson(LocalDate.parse("2000-01-01")));

        PersonDto personData = personService.getPersonData(1L);

        verify(personRepository).findById(1L);

        assertEquals("person1", personData.getName());
        assertEquals("person1lastName", personData.getLastName());
        assertEquals(22, personData.getAge());

    }

    @Test
    void getPersonDataWithWrongDateFormat() {

        assertThrows(DateTimeParseException.class,
                () -> {
                    LocalDate localDate = LocalDate.parse("2000-01");
                    when(personRepository.findById(1L)).thenReturn(returnPerson(localDate));
                });

        personService.getPersonData(1L);

    }


    @Test
    void getPersonDataWhenDateIsNull() {
        when(personRepository.findById(1L)).thenReturn(returnPerson(null));

        assertThrows(InvalidParameterException.class,
                () -> {
                    personService.getPersonData(1L);
                });

    }

    @Test
    void getPersonDataWhenIdIsWrong() {

        lenient().when(personRepository.findById(2L)).thenReturn(returnPersonWrongId(2L));

        personService.getPersonData(1L);

    }


    private Optional<Person> returnPerson(LocalDate date) {
        Person person = new Person();
        person.setId(1L);
        person.setName("person1");
        person.setLastName("person1lastName");
        person.setDate(date);
        return Optional.of(person);
    }

    private Optional<Person> returnPersonWrongId(Long id) {
        Person person = new Person();
        person.setId(id);
        person.setName("person1");
        person.setLastName("person1lastName");
        person.setDate(LocalDate.parse("2000-01-01"));
        return Optional.of(person);
    }
}