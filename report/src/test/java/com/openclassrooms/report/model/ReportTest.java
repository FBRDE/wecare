package com.openclassrooms.report.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    // *******************************************************************


    @Test
    public void testGetReportValues() {
        Report report=new Report("lastName","firstName","F",50,Risk.None);

        assertEquals(50, report.getAge());
        assertEquals("firstName", report.getFirstName());
        assertEquals("lastName", report.getLastName());
        assertEquals(Risk.None, report.getRisk());
        assertEquals("F", report.getGender());


    }


    // *******************************************************************



    @Test
    public void testSetReportValues() {

        Report report=new Report();

        report.setAge(50);
        report.setFirstName("firstName");
        report.setLastName("lastName");
        report.setRisk(Risk.None);
        report.setGender("F");

        assertEquals(50, report.getAge());
        assertEquals("firstName", report.getFirstName());
        assertEquals("lastName", report.getLastName());
        assertEquals(Risk.None, report.getRisk());
        assertEquals("F", report.getGender());
    }

}
