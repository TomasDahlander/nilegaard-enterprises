package com.example.nilegaardenterprises.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate birthdate;
    private String phone;

}
