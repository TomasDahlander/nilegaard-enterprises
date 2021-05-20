package com.example.nilegaardenterprises.repositories;

import com.example.nilegaardenterprises.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 10:04 <br>
 * Project: nilegaard-enterprises <br>
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    
    boolean existsByFirstNameAndLastNameAndBirthdate(String firstName, String lastName, String birthdate);

    Person findByPhone(String phone);
    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findAllByFirstName(String firstName);
    List<Person> findAllByLastName(String lastName);
    Person deleteByFirstNameAndLastName(String firstName, String lastName);
}
