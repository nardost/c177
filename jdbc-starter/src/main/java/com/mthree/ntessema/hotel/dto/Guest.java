package com.mthree.ntessema.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
    private String streetAddress;
    private String city;
    private State state;
    private int zipCode;
}
