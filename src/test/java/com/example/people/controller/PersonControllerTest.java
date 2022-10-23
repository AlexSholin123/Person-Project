package com.example.people.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findPerson() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/person/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("person1")))
                .andExpect(jsonPath("$.lastName", is("person1lastName")))
                .andExpect(jsonPath("$.age", is(22)));

    }

    @Test
    public void findNoPerson() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/person/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(nullValue())))
                .andExpect(jsonPath("$.lastName", is(nullValue())))
                .andExpect(jsonPath("$.age", is(0)));

    }

}
