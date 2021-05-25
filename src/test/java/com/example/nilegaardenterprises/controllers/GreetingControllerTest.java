package com.example.nilegaardenterprises.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-25 <br>
 * Time: 13:11 <br>
 * Project: nilegaard-enterprises <br>
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void welcomeMessageTest() throws Exception {

        String welcomeMessage = "Welcome to Nilecity person book!<br>" +
                "person/all = Get all persons<br>" +
                "person/findByPhone/{phone number} = Get the person with that phone number<br>" +
                "person/findByFirstNameAndLastName/{firstName}/{lastName} = Get that specific person<br>" +
                "person/findByFirstName/{firstName} = Get all persons with that firstname<br>" +
                "person/findByLastName/{lastName} = Get all persons with that lastname<br>" +
                "person/deleteOnePerson/{firstName}/{lastName} = Delete that specific person from the book<br>" +
                "person/findByBirthdate/above/{birthYear} = Get the persons whose birth-year is higher than input<br>" +
                "person/findByBirthdate/below/{birthYear} = Get the persons whose birth-year is lower than input<br>";

        mvc.perform(MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(welcomeMessage));
    }
}