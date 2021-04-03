package com.ocr.p9PatientRisk.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Model tests
 */
public class PatientTest {

    @Test
    void patientModelTest () {
        LocalDate birth = LocalDate.of(2000,1,15);
        LocalDateTime dtTest = LocalDateTime.now();
        PatientDTO patient = new PatientDTO();
        patient.setId(1);
        patient.setAddress("12 rue des oliviers");
        patient.setFamilly("TestFamille");
        patient.setGiven("Test");
        patient.setSex("M");
        patient.setBirthDate(birth);
        patient.setCreateDate(dtTest);
        patient.setUpdateDate(dtTest);
        assertTrue(patient != null);
        assertTrue(patient.getSex().equals("M"));
        assertTrue(patient.getAddress().equals("12 rue des oliviers"));
        assertTrue(patient.getFamilly().equals("TestFamille"));
        assertTrue(patient.getGiven().equals("Test"));
        assertTrue(patient.getId() != null);
        assertTrue(patient.getBirthDate() != null);
    }

    @Test
    void patientContructorTest () {
        LocalDate birth = LocalDate.of(2000,1,15);
        PatientDTO patient = new PatientDTO("Martin","Olivier","25 rue des pinsons","0102030405","M",birth);
        assertTrue(patient != null);
        assertTrue(patient.getSex().equals("M"));
        assertTrue(patient.getAddress().equals("25 rue des pinsons"));
        assertTrue(patient.getFamilly().equals("Martin"));
        assertTrue(patient.getGiven().equals("Olivier"));
        assertTrue(patient.getBirthDate() != null);
    }
}
