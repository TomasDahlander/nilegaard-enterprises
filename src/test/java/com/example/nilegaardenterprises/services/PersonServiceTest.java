package com.example.nilegaardenterprises.services;

import com.example.nilegaardenterprises.models.Person;
import com.example.nilegaardenterprises.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
                new Person("1","Orvar", "Karlsson", "1000-10-10", "123456789"),
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
    void saveNewPersonThrowErrorTest() {
        when(mockRepository.existsByFirstNameAndLastNameAndBirthdate(
                "Orvar","Karlsson","1000-10-10"))
                .thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> personService.saveNewPerson(
                new Person("Orvar", "Karlsson", "1000-10-10", "123456789")));
    }

    @Test
    void getAllPersonsTest() {
        when(mockRepository.findAll()).thenReturn(list);

        List<Person> expected = list;
        List<Person> actual = personService.getAllPersons();

        assertEquals(actual, expected);
        assertNotEquals(null, actual);
        verify(mockRepository).findAll();
    }

    @Test // Lista
    void saveNewPersonsTest() {
        when(mockRepository.save(list.get(0))).thenReturn(list.get(0));
        when(mockRepository.save(list.get(1))).thenReturn(list.get(1));
        when(mockRepository.save(list.get(2))).thenReturn(list.get(2));

        List<Person> expected = list;
        List<Person> actual = personService.saveNewPersons(list);

        assertEquals(actual, expected);

        for(Person person : list) {
            verify(mockRepository).save(person);
        }
    }

    @Test
    void findPersonByPhoneTest() {
        when(mockRepository.findByPhone("7777777777")).thenReturn(list.get(2));

        Person expected = list.get(2);
        Person notExpected = list.get(1);
        Person actual = personService.findPersonByPhone("7777777777");

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

        verify(mockRepository).findByPhone(anyString());
    }

    @Test
    void findPersonByFirstNameTest() {
        List<Person> expected = Arrays.asList(list.get(0));
        List<Person> notExpected = Arrays.asList(list.get(1));

        when(mockRepository.findAllByFirstName("Orvar")).thenReturn(expected);
        List<Person> actual = personService.findAllPersonsByFirstName("Orvar");

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

        verify(mockRepository).findAllByFirstName(anyString());
    }

    @Test
    void findPersonByLastNameTest() {
        List<Person> expected = Arrays.asList(list.get(2));
        List<Person> notExpected = Arrays.asList(list.get(1));

        when(mockRepository.findAllByLastName("Skywalker")).thenReturn(expected);
        List<Person> actual = personService.findAllPersonsByLastName("Skywalker");

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

        verify(mockRepository).findAllByLastName(anyString());
    }

    @Test
    void findPersonByFirstNameAndLastNameTest(){
        Person expected = list.get(0);

        when(mockRepository.findByFirstNameAndLastName(
                "Orvar", "Karlsson"
        )).thenReturn(list.get(0));

        Person actual = personService.findPersonByFirstNameAndLastName("Orvar", "Karlsson");

        assertEquals(expected, actual);
    }

    @Test
    void deleteAllPersonsTest() {
        when(mockRepository.findAll()).thenReturn(list);

        String expected = "All persons below removed from database:\n";
        for (Person person : list) {
            expected += person.getFirstName() + " " + person.getLastName() + " deleted.\n";
        }

        String actual = personService.deleteAllPersons();

        assertEquals(expected, actual);

    }
}
