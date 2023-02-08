package com.openclassrooms.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.patient.model.Patient;
import com.openclassrooms.patient.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerUTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    PatientService patientService;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void getAllPatientsTest() throws Exception {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "adress","0123");
        List<Patient> patientsList=new ArrayList<>();
        patientsList.add(patient);

        when(patientService.getAllPatient()).thenReturn(patientsList);

        mockMvc.perform(get("/patients"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$.[0].firstName", is("firstName_test")))
                .andExpect(jsonPath("$.[0].lastName", is("lastName_test")))
                .andReturn();
    }

    @Test
    public void getPatientByIdTest() throws Exception {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "adress","0123");

        when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/patient/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstName_test")))
                .andExpect(jsonPath("$.lastName", is("lastName_test")))
                .andReturn();
    }


    @Test
    public void addPatientTest() throws Exception {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "adress","0123");

        when(patientService.addPatient(patient)).thenReturn(patient);
        mockMvc.perform(post("/patient/add").content(mapper.writeValueAsString(patient))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void updatePatientTest() throws Exception {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test_update","lastName_test_update",newDate,"M",
                "adress","0123");
        Patient patientToUpdate=new Patient(1,"firstName_test","lastName_test",newDate,"M",
                "adress","0123");
        when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
        when(patientService.updatePatient(patient,1)).thenReturn(patient);

        mockMvc.perform(put("/patient/update/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(patient)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void deletePatientTest() throws Exception {
        LocalDate newDate = LocalDate.of(2021,12,31);
        Patient patient=new Patient(1,"firstName_test_update","lastName_test_update",newDate,"M",
                "adress","0123");
        when(patientService.deletePatient(1)).thenReturn(patient);
        mockMvc.perform(delete("/patient/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}

