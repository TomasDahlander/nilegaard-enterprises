package com.example.nilegaardenterprises.services;

import com.example.nilegaardenterprises.models.Person;
import com.example.nilegaardenterprises.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 10:33 <br>
 * Project: nilegaard-enterprises <br>
 */
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    PersonService personService;

    @Mock
    PersonRepository mockRepository;

    List<Person> list;

    @BeforeEach
    public void init() {
        list = Arrays.asList(
                new Person("Orvar", "Karlsson", "1000-10-10", "123456789"),
                new Person("Darth", "Vader", "6666-66-66", "6666666666"),
                new Person("Luke", "Skywalker", "2222-22-22", "7777777777")
        );
        personService = new PersonService(mockRepository);
    }

    @Test
    void saveNewPersonTest() {
        when(mockRepository.save(list.get(0))).thenReturn(list.get(0));

        Person actual = personService.saveNewPerson(list.get(0));

        assertEquals(actual, list.get(0));
        verify(mockRepository).save(any(Person.class));
    }


    @Test
    void getAllPersonsTest() {
    }

    @Test
    void saveNewPersonsTest() {
    }

    @Test
    void findPersonByPhoneTest() {
    }

    @Test
    void findPersonByFirstName() {

    }
}