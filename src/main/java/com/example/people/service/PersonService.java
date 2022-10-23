package com.example.people.service;

import com.example.people.dto.PersonDto;
import com.example.people.models.Person;
import com.example.people.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonDto getPersonData(Long id) {
        Optional<Person> person = personRepository.findById(id);
        try {
            return person.map(value -> PersonDto.builder()
                    .name(value.getName())
                    .lastName(value.getLastName())
                    .age(countAge(value.getDate()))
                    .build()).orElseThrow();
        } catch (NoSuchElementException e) {
            return PersonDto.builder().build();
        }

    }

    private int countAge(LocalDate localDate) {
        if (Objects.isNull(localDate)) {
            throw new InvalidParameterException();
        }
        LocalDate dob = LocalDate.parse(localDate.toString());
        return calculateAge(dob);
    }

    private static int calculateAge(LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        if (dob != null) {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }

}
