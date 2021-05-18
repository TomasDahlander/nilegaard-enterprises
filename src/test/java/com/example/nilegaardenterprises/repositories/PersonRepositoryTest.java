package com.example.nilegaardenterprises.repositories;

import com.example.nilegaardenterprises.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 10:32 <br>
 * Project: nilegaard-enterprises <br>
 */
@DataMongoTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    List<Person> persons;

    @BeforeEach
    void init(){
        persons = Arrays.asList(
            new Person("Arne","Aronsson","1975-01-15","070-111 45 67"),
            new Person("Bengt","Bush","1965-02-25","070-222 22 33"),
            new Person("Clas","Clabbe","1989-03-13","070-333 66 99"),
            new Person("David","Druid","1970-04-14","070-444 44 66"),
            new Person("David","Bush","1972-04-14","070-555 22 88")
        );
        personRepository.saveAll(persons);
    }

    @AfterEach
    void clearDb() {
        personRepository.deleteAll();
    }

    @Test
    void existsByFirstNameAndLastNameAndBirthdateTest() {
        boolean actualTrue = personRepository.existsByFirstNameAndLastNameAndBirthdate("Arne","Aronsson","1975-01-15");
        boolean actualFalse = personRepository.existsByFirstNameAndLastNameAndBirthdate("Bengan","Boys","1975-01-15");

        assertTrue(actualTrue);
        assertFalse(actualFalse);
    }

    @Test
    void findByPhoneTest() {
        Person expected = persons.get(1);
        Person actual = personRepository.findByPhone("070-222 22 33");
        assertEquals(actual, expected);
    }

    @Test
    void findByFirstNameAndLastNameTest() {
        Person expectedTrue = persons.get(0);
        Person expectedFalse = persons.get(1);

        Person actualTrue = personRepository.findByFirstNameAndLastName("Arne", "Aronsson");
        Person actualFalse = personRepository.findByFirstNameAndLastName("Bengan", "Bushen");

        assertEquals(expectedTrue, actualTrue);
        assertNotEquals(expectedFalse, actualFalse);
    }

    @Test
    void findAllByFirstNameTest() {
        List<Person> expected = Arrays.asList(
                persons.get(3),
                persons.get(4)
        );
        List<Person> actual = personRepository.findAllByFirstName("David");
        assertEquals(expected,actual);
    }

    @Test
    void findAllByLastNameTest() {
        List<Person> expected = Arrays.asList(
                persons.get(1),
                persons.get(4)
        );
        List<Person> actual = personRepository.findAllByLastName("Bush");
        assertEquals(expected,actual);
    }
}