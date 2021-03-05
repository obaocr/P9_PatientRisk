package com.ocr.p9PatientRisk.controller;

import com.ocr.p9PatientRisk.model.PatientRiskDTO;
import com.ocr.p9PatientRisk.service.PatientRiskService;
import com.ocr.p9PatientRisk.util.EntityIllegalArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controler for PatientRisk
 */
@RestController
public class PatientRiskControler {

    private static final Logger log = LogManager.getLogger(PatientRiskControler.class);

    @Autowired
    PatientRiskService patientRiskService;

    @GetMapping("/")
    public String patientRiskHome() {
        log.debug("patientRiskHome");
        return "P9 Patient Risk Home";
    }

    // TODO gérer not found ?

    @GetMapping("/assess/{Id}")
    public PatientRiskDTO getPatientRiskById(@PathVariable Integer Id) {
        log.debug("getPatientRiskById");
        if (Id == null || Id == 0 ) {
            log.error("The parameter Id is mandatory and must be > 0");
            throw new EntityIllegalArgumentException("The parameter Id is mandatory and must be > 0");
        }
        return patientRiskService.getPatientRisk(Id);
    }

    @GetMapping("/assess")
    public List<PatientRiskDTO> getPatientRiskByCriteria(@RequestParam String familly) {
        log.debug("getPatientRiskByCriteria");
        if (familly.isEmpty()) {
            log.error("The parameter familly is mandatory");
            throw new EntityIllegalArgumentException("The parameter familly is mandatory");
        }
        return patientRiskService.getPatientRiskByFamilly(familly);
    }

}
