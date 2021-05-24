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
                "/all = Get all persons<br>" +
                "/findByPhone/{phone number} = Get the person with that phone number<br>" +
                "/findByFirstNameAndLastName/{firstName}/{lastName} = Get that specific person<br>" +
                "/findByFirstName/{firstName} = Get all persons with that firstname<br>" +
                "/findByLastName/{lastName} = Get all persons with that lastname<br>" +
                "/deleteOnePerson/{firstName}/{lastName} = Delete that specific person from the book<br>" +
                "/findByBirthdate/above/{birthYear} = Get the persons whose birth-year is higher than input<br>" +
                "/findByBirthdate/below/{birthYear} = Get the persons whose birth-year is lower than input<br>";
    }
}
