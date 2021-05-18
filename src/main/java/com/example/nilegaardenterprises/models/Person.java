package com.example.nilegaardenterprises.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-18 <br>
 * Time: 09:35 <br>
 * Project: nilegaard-enterprises <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String phone;

    public Person(String firstName, String lastName, String birthdate, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phone = phone;
    }
}
