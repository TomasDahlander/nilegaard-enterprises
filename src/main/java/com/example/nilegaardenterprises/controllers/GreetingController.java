package com.example.nilegaardenterprises.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 09:59 <br>
 * Project: nilegaard-enterprises <br>
 */
@RequiredArgsConstructor
@RestController
public class GreetingController {

    @GetMapping("/")
    public String welcomeMessage(){
        return "Welcome to Nilecity person book!<br>" +
                "person/all = Get all persons<br>" +
                "person/findByPhone/{phone number} = Get the person with that phone number<br>" +
                "person/findByFirstNameAndLastName/{firstName}/{lastName} = Get that specific person<br>" +
                "person/findByFirstName/{firstName} = Get all persons with that firstname<br>" +
                "person/findByLastName/{lastName} = Get all persons with that lastname<br>" +
                "person/deleteOnePerson/{firstName}/{lastName} = Delete that specific person from the book<br>" +
                "person/findByBirthdate/above/{birthYear} = Get the persons whose birth-year is higher than input<br>" +
                "person/findByBirthdate/below/{birthYear} = Get the persons whose birth-year is lower than input<br>";
    }
}
