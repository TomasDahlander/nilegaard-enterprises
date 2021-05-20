package com.example.nilegaardenterprises.controllers;

import com.example.nilegaardenterprises.models.Person;
import com.example.nilegaardenterprises.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 09:59 <br>
 * Project: nilegaard-enterprises <br>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/")
    public String welcomeMessage(@RequestParam(required = false) String name ){
        if(name == null) name = "Orvar Karlsson";
        return "Hi and welcome to this controller " + name;
    }

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/save")
    public Person saveNewPerson(@RequestBody Person person) {
        return personService.saveNewPerson(person);
    }

    @PostMapping("/save/list")
    public List<Person> saveNewPersons(@RequestBody List<Person> persons){
        return personService.saveNewPersons(persons);
    }

    @GetMapping("/findByPhone/{phone}")
    public Person findPersonByPhone(@PathVariable String phone){
        return personService.findPersonByPhone(phone);
    }

    @GetMapping("/findByFirstNameAndLastName/{firstName}/{lastName}")
    public Person findPersonByNames(@PathVariable String firstName, @PathVariable String lastName) {
        return personService.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/findByFirstName/{firstName}")
    public List<Person> findPersonByFirstName(@PathVariable String firstName) {
        return personService.findAllPersonsByFirstName(firstName);
    }

    @GetMapping("findByLastName/{lastName}")
    public List<Person> findPersonByLastName(@PathVariable String lastName) {
        return personService.findAllPersonsByLastName(lastName);
    }

    @DeleteMapping("/deleteAllPersons")
    public String deleteAll() {
        return personService.deleteAllPersons();
    }

    @GetMapping("deleteOnePerson/{firstName}/{lastName}")
    public String deleteOnePerson(@PathVariable String firstName, @PathVariable String lastName) {
        return personService.deleteOnePerson(firstName, lastName);
    }

}
