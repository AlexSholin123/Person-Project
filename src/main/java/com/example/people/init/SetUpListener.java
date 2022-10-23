package com.example.people.init;

import com.example.people.models.Person;
import com.example.people.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SetUpListener {

    @Autowired
    PersonRepository personRepository;

    @EventListener
    public void setUpDataBase(ApplicationReadyEvent applicationReadyEvent) {
        Person person = new Person();
        person.setName("person1");
        person.setLastName("person1lastName");
        person.setDate(LocalDate.parse("2000-01-01"));
        personRepository.save(person);
    }
}
