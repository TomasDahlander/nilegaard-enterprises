package com.example.nilegaardenterprises.services;

import com.example.nilegaardenterprises.models.Person;
import com.example.nilegaardenterprises.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 10:02 <br>
 * Project: nilegaard-enterprises <br>
 */
@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Person saveNewPerson(Person person) {
       if(!checkIfPersonExists(person)) {
          return personRepository.save(person);
       }
       throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Person is already in database.");
    }

    private boolean checkIfPersonExists(Person person){
        if(person.getBirthdate().length() != 10) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Birthdate must be in format XXXX-XX-XX");
        return personRepository.existsByFirstNameAndLastNameAndBirthdate(
                person.getFirstName(), person.getLastName(), person.getBirthdate());
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> saveNewPersons(List<Person> persons) {
        List<Person> list = new ArrayList<>();
        for(Person p : persons){
            list.add(saveNewPerson(p));
        }
        return list;
    }

    public Person findPersonByPhone(String phone) {
        return personRepository.findByPhone(phone);
    }

    public Person findPersonByFirstNameAndLastName(String firstName, String lastName) {
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Person> findAllPersonsByFirstName(String firstName) {
        return personRepository.findAllByFirstName(firstName);
    }

    public List<Person> findAllPersonsByLastName(String lastName) {
        return personRepository.findAllByLastName(lastName);
    }

    public String deleteAllPersons() {
        List<Person> list = personRepository.findAll();
        String message = "All persons below removed from database:\n";
        if (list.size() == 0) {
            message = "No persons found in database";
        } else  {
            for (Person person : list) {
                message += person.getFirstName() + " " + person.getLastName() + " deleted.\n";
            }
            personRepository.deleteAll();
        }
        return message;
    }
}
