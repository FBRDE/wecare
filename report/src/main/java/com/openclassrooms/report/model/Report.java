package com.openclassrooms.report.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    private String lastName;
    private String firstName;
    private String gender;
    private long age;
    private Risk risk;
}
