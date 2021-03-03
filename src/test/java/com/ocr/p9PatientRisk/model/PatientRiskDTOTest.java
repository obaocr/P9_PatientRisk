package com.ocr.p9PatientRisk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientRiskDTOTest {

    @Test
    void testPatientRiskDTO() {
        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(1);
        patientRiskDTO.setPatientInfo("test info");
        patientRiskDTO.setRisk("None");
        assertTrue(patientRiskDTO.getPatientId() == 1);
        assertTrue(patientRiskDTO.getPatientInfo().equals("test info"));
        assertTrue(patientRiskDTO.getPatientInfo().equals("None"));
    }

}
