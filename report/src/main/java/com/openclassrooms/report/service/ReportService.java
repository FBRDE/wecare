package com.openclassrooms.report.service;

import com.openclassrooms.report.model.*;
import com.openclassrooms.report.proxy.NoteProxy;
import com.openclassrooms.report.proxy.PatientProxy;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("declencheurs")
public class ReportService {
    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;
    private final String[] declencheurs;

    public ReportService(NoteProxy noteProxy, PatientProxy patientProxy, @Value("${declencheurs}") String[] declencheurs) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
        this.declencheurs = declencheurs;

    }

    public long getPatientAge (Patient patient) {

        return ChronoUnit.YEARS.between(patient.getBirthDate(), LocalDate.now());
    }

    public Risk calculateRisk (Patient patient, List<Note> notes) {
        long factorsNb = calculateFactorsNb(notes);
        long age = getPatientAge(patient);

        if (age < 30) {
            boolean M = patient.getGender().contentEquals("M");
            if (factorsNb >= (M ? 5 : 7)) {
                return Risk.EarlyOnset;
            }
            if (factorsNb >= (M ? 3 : 4)) {
                return Risk.InDanger;
            }
        } else {
            if (factorsNb >= 8) {
                return Risk.EarlyOnset;
            }
            if (factorsNb >= 6) {
                return Risk.InDanger;
            }
            if (factorsNb >= 2) {
                return Risk.Borderline;
            }
        }
        return Risk.None;
    }

    public long calculateFactorsNb(List<Note> notes) {

        String noteToStream = notes.stream()
                .map(Note::getNote)
                .map(String::trim)
                .collect(Collectors.joining()).toLowerCase();

        long factorsNb = Arrays.stream(declencheurs)
                .filter(noteToStream::contains)
                .distinct()
                .count();
        return factorsNb;

    }

    public Report getReportByPatientId(int id) {

        Patient patient = patientProxy.getPatientById(id);
        List<Note> notes = noteProxy.getNoteByPatientId(patient.getId());

        Risk risk = calculateRisk(patient, notes);

        Report report = new Report(patient.getLastName(), patient.getFirstName(), patient.getGender(), getPatientAge(patient), risk);
        return report;
    }
}
