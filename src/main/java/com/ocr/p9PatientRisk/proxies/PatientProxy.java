package com.ocr.p9PatientRisk.proxies;

import com.ocr.p9PatientRisk.model.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Proxy for Patient / micro service
 */
@FeignClient(name = "microservice-patient", url = "http://localhost:8045")
public interface PatientProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/patient/{Id}")
    PatientDTO getPatientById(@PathVariable("Id") Integer Id);

    @RequestMapping(method = RequestMethod.GET, value = "/patient")
    List<PatientDTO> getPatientByFamilly(@RequestParam("familly") String familly);

}
