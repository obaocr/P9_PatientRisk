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
        patientRiskDTO.setNbrisk(5);
        assertTrue(patientRiskDTO.getPatientId() == 1);
        assertTrue(patientRiskDTO.getPatientInfo().equals("test info"));
        assertTrue(patientRiskDTO.getRisk().equals("None"));
        assertTrue(patientRiskDTO.getNbrisk() == 5);
    }

}
