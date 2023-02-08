package com.openclassrooms.patient.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {

    // *******************************************************************


    @Test
    public void testGetPatientValues() {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");

        assertEquals(1, patient.getId());
        assertEquals("firstName_test", patient.getFirstName());
        assertEquals("lastName_test", patient.getLastName());
        assertEquals(newDate, patient.getBirthDate());
        assertEquals("M", patient.getGender());
        assertEquals("address", patient.getAddress());
        assertEquals("0123", patient.getPhoneNumber());

    }


    // *******************************************************************



    @Test
    public void testSetPatientValues() {

        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");

        patient.setId(2);
        patient.setFirstName("update_firstName");
        patient.setLastName("update_lastName");
        patient.setBirthDate(LocalDate.of(2020,10,20));
        patient.setGender("F");
        patient.setAddress("update_address");
        patient.setPhoneNumber("456");


        assertEquals(2, patient.getId());
        assertEquals("update_firstName", patient.getFirstName());
        assertEquals("update_lastName", patient.getLastName());
        assertEquals(LocalDate.of(2020,10,20), patient.getBirthDate());
        assertEquals("F", patient.getGender());
        assertEquals("update_address", patient.getAddress());
        assertEquals("456", patient.getPhoneNumber());
    }

}
