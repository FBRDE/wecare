package com.openclassrooms.report.proxy;
import com.openclassrooms.report.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "patient", url = "${PROXY_PATIENT:http://localhost:8081}",decode404 = true)
public interface PatientProxy {

    @GetMapping(value = "/patients")
    List<Patient> getAllPatient();
    @GetMapping(value = "/patient/{id}")
    Patient getPatientById (@PathVariable Integer id);
}
