package com.ocr.p9PatientRisk.proxies;

import com.ocr.p9PatientRisk.model.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy for Patient / micro service
 */
@FeignClient(name = "microservice-patient", url = "http://localhost:8045")
public interface PatientProxy {

    @GetMapping(value = "/Patients")
    List<PatientDTO> getPatients();

    @RequestMapping(method = RequestMethod.GET, value = "/Patient")
    PatientDTO getPatientById(@RequestParam("Id") Integer Id);

    @PostMapping(value = "/Patient")
    Integer addPatient(@RequestBody PatientDTO patient);

    @RequestMapping(method = RequestMethod.PUT, value = "/Patient/{Id}")
    Boolean updatePatient(@RequestParam Integer Id, @RequestBody PatientDTO patient);

    @RequestMapping(method = RequestMethod.DELETE, value = "/Patient/{Id}")
    Boolean deletePatient(@RequestParam Integer Id);

}
