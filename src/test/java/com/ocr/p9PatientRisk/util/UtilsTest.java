package com.ocr.p9PatientRisk.util;

import com.ocr.p9PatientRisk.model.NoteDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Test
    void calculateAgeTest() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dtTest = LocalDate.of(2020, 1, 16);
        assertTrue(Utils.calculateAge(birth, dtTest) == 20);
    }

    @Test
    void searchItemsShouldReturnEmptyMapTest() {
        List<NoteDTO> notes = new ArrayList<>();
        String[] declencheurs = new String[]{"Hémoglobine A1C","Microalbumine","Taille","Poids","Fumeur"
                ,"Anormal","Cholestérol","Vertige","Rechute","Réaction","Anticorps"};

        LocalDateTime dtTest = LocalDateTime.now();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNoteId("1");
        noteDTO.setPatientId(1);
        noteDTO.setTitle("Test title");
        noteDTO.setNote("Test note");
        noteDTO.setCreateDate(dtTest);
        noteDTO.setUpdateDate(dtTest);
        notes.add(noteDTO);

        Map<String, Integer> mapResult = new HashMap<>();
        mapResult = Utils.searchItems(declencheurs, notes);

        assertTrue(mapResult.size() == 0);
    }

    @Test
    void searchItemsShouldReturnMapWithEntriesTest() {
        List<NoteDTO> notes = new ArrayList<>();
        String[] declencheurs = new String[]{"Hémoglobine A1C","Microalbumine","Taille","Poids","Fumeur"
                ,"Anormal","Cholestérol","Vertige","Rechute","Réaction","Anticorps"};

        LocalDateTime dtTest = LocalDateTime.now();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setNoteId("1");
        noteDTO.setPatientId(1);
        noteDTO.setTitle("Test title");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal" +
                ",Cholestérol,Vertige,Rechute,Réaction,Anticorps");
        noteDTO.setCreateDate(dtTest);
        noteDTO.setUpdateDate(dtTest);
        notes.add(noteDTO);

        Map<String, Integer> mapResult = new HashMap<>();
        mapResult = Utils.searchItems(declencheurs, notes);

        assertTrue(mapResult.size() == 11);
    }



}
