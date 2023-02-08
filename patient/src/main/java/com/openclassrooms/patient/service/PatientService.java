package com.openclassrooms.patient.service;

import com.openclassrooms.patient.model.Patient;
import com.openclassrooms.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
    public Patient addPatient(Patient patient)
    {
       return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient, Integer id)
    {

        Optional<Patient> patientToUpdate = patientRepository.findById(id);

        if(patientToUpdate.isPresent())
        {
        patientToUpdate.get().setFirstName(patient.getFirstName());
        patientToUpdate.get().setLastName(patient.getLastName());
        patientToUpdate.get().setBirthDate(patient.getBirthDate());
        patientToUpdate.get().setGender(patient.getGender());
        patientToUpdate.get().setAddress(patient.getAddress());
        patientToUpdate.get().setPhoneNumber(patient.getPhoneNumber());
        return patientRepository.save(patientToUpdate.get());
        }
        else return null;
    }

    public Patient deletePatient(Integer id)
    {
        Optional<Patient> patientToDelete = patientRepository.findById(id);
        if(patientToDelete.isPresent())
        {
            patientRepository.deleteById(id);
            return patientToDelete.get();
        }
        else return null;
    }

    public Optional<Patient> getPatientById(Integer id) {
       return patientRepository.findById(id);
    }

}