package com.openclassrooms.report.controller;


import com.openclassrooms.report.model.Report;
import com.openclassrooms.report.service.ReportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${wecare.front.cross.origin}"})
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;


    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/patient/{id}")
    public ResponseEntity<Report> getReportByPatientId (@PathVariable Integer id) {
        LOGGER.info("GET Rapport with ID : {} OK", id);
        Report report = reportService.getReportByPatientId(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
