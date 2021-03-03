package com.ocr.p9PatientRisk.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Test
    void calculateAgeTest() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dtTest = LocalDate.of(2020, 1, 16);
        ;
        assertTrue(Utils.calculateAge(birth, dtTest) == 20);
    }
}
