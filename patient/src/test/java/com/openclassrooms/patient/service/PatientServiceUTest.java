package com.openclassrooms.patient.service;

import com.openclassrooms.patient.model.Patient;
import com.openclassrooms.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class PatientServiceUTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void getAllPatientTest() {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");
        List<Patient> patientsList=new ArrayList<>();
        patientsList.add(patient);

        when(patientRepository.findAll()).thenReturn(patientsList);
        assertEquals(patientService.getAllPatient(),patientsList);

    }
    @Test
    public void addPatientTest()
    {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient patientActual = patientService.addPatient(patient);
        assertEquals(patientActual,patient);

    }
    @Test
    public void updatePatientTest()
    {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patientToUpdate=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");

        doReturn(Optional.of(patientToUpdate)).when(patientRepository).findById(1);
        when(patientRepository.save(patientToUpdate)).thenReturn(patientToUpdate);
        Patient patientActual = patientService.updatePatient(patientToUpdate,1);
        assertEquals(patientActual,patientToUpdate);
    }

    @Test
    public void deletePatientTest()
    {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patientToDelete=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");

        doReturn(Optional.of(patientToDelete)).when(patientRepository).findById(1);
        doNothing().when(patientRepository).deleteById(1);
        Patient patientActual = patientService.deletePatient(1);
        assertEquals(patientActual,patientToDelete);
    }

    @Test
    public void getPatientByIdTest()
    {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "address","0123");
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        Patient patientActual = patientService.getPatientById(1).get();
        assertEquals(patientActual,patient);
    }
}

