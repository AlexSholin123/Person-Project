package com.example.people.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    String name;
    String lastName;
    int age;
}
