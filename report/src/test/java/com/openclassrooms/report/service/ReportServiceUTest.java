package com.openclassrooms.report.service;

import com.openclassrooms.report.model.Patient;
import com.openclassrooms.report.proxy.NoteProxy;
import com.openclassrooms.report.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@ContextConfiguration(classes = ReportService.class)
@TestPropertySource(locations = "/declencheurs")
public class ReportServiceUTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void getPatientAgeTest() {

        LocalDate newDate = LocalDate.of(2010,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");

        assertEquals(reportService.getPatientAge(patient),12);

    }

}

