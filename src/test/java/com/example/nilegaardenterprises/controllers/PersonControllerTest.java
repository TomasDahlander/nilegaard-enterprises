package com.example.nilegaardenterprises.controllers;

import com.example.nilegaardenterprises.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-25 <br>
 * Time: 11:01 <br>
 * Project: nilegaard-enterprises <br>
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllPersons() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/al")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void saveNewPerson() throws Exception {
        Person person = new Person("Orvar","Karlsson","1985-08-12","070-123 45 67");
        mvc.perform(MockMvcRequestBuilders.post("/person/save")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mvc.perform(MockMvcRequestBuilders.post("/person/sa")
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void saveNewPersons() throws Exception {
        List<Person> list = Arrays.asList(
                new Person("Orvar","Karlsson","1985-08-12","070-123 45 67"),
                new Person("Percy","Nilegaard","1956-03-15","070-101 01 01")
        );

        mvc.perform(MockMvcRequestBuilders.post("/person/save/list")
                .content(asJsonString(list))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mvc.perform(MockMvcRequestBuilders.post("/person/save/li")
                .content(asJsonString(list))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findPersonByPhone() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByPhone/0701234567")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findByPh/0701234567")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void findPersonByNames() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByFirstNameAndLastName/Orvar/Karlsson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findByFirstNameAndLastNa/Percy/Nilegaard")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void findPersonByFirstName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByFirstName/Orvar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findByFirstNa/Percy")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void findPersonByLastName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByLastName/Karlsson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findLastName/Karlsson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void deleteAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/person/deleteAllPersons")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.delete("/person/deleteAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void deleteOnePerson() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/person/deleteOnePerson/Orvar/Karlsson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/deleteOne/Orvar/Karlsson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void findPersonByBirthdateAbove() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByBirthdate/above/1989")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findByBirthdate/abo/1989")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    void findPersonByBirthdateBelow() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/findByBirthdate/below/1975").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().is(200));
        mvc.perform(MockMvcRequestBuilders.get("/person/findByBirthdate/bel/1975")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
}