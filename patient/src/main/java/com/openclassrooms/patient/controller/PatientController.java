package com.openclassrooms.patient.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openclassrooms.patient.model.Patient;
import com.openclassrooms.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@CrossOrigin(origins = {"${wecare.front.cross.origin}", "${report.cross}"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientController {

    @Autowired
    PatientService patientService;

    private static final Logger logger = LogManager.getLogger("PatientController");

    @GetMapping(value="/patients")
    public List<Patient> getAllPatient( ){
        return patientService.getAllPatient();
    }

    @GetMapping(value="/patient/{id}")
    public Patient getPatientById (@PathVariable Integer id){
        return patientService.getPatientById(id).get();
    }

    @PostMapping(value="/patient/add")
    public ResponseEntity addPatient(@RequestBody Patient patient)  {

        patientService.addPatient(patient);
        LOGGER.info("Create Patient OK : " + patient.getId());
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PutMapping(value="/patient/update/{id}")
    public ResponseEntity updatePatient(@RequestBody Patient patient,@PathVariable("id") Integer id)  {
        if (!patientService.getPatientById(id).isPresent()) {
            LOGGER.info("Error updating Patient with ID : " + id);
            return ResponseEntity.badRequest().build();
        }
        patientService.updatePatient(patient,id);
        LOGGER.info("update Patient OK : " + id);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @DeleteMapping(value="/patient/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable("id") Integer id)  {
        Patient patientDeleted =patientService.deletePatient(id);
        if (Objects.isNull(patientDeleted)) {
            return ResponseEntity.noContent().build();
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }
}








