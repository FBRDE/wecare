package com.openclassrooms.report.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter  @Setter

public class Patient {

    private Integer id;
    private String lastName ;
    private String firstName ;
    private LocalDate birthDate ;
    private String gender ;
    private String address;
    private String phoneNumber;


}


