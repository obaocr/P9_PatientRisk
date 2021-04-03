package com.ocr.p9PatientRisk.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest {

    @Test
    void noteModelTest() {
        LocalDateTime dtTest = LocalDateTime.now();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNoteId("1");
        noteDTO.setPatientId(1);
        noteDTO.setTitle("Test title");
        noteDTO.setNote("Test note");
        noteDTO.setCreateDate(dtTest);
        noteDTO.setUpdateDate(dtTest);
        assertTrue(noteDTO != null);
        assertTrue(noteDTO.getNoteId().equals("1"));
        assertTrue(noteDTO.getPatientId() == 1);
        assertTrue(noteDTO.getTitle().equals("Test title"));
        assertTrue(noteDTO.getNote().equals("Test note"));
        assertTrue(noteDTO.getUpdateDate() != null);
        assertTrue(noteDTO.getCreateDate() != null);
    }

}
