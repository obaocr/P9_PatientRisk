package com.ocr.p9PatientRisk.service;

import com.ocr.p9PatientRisk.model.NoteDTO;
import com.ocr.p9PatientRisk.model.PatientDTO;
import com.ocr.p9PatientRisk.model.PatientRiskDTO;
import com.ocr.p9PatientRisk.proxies.NoteProxy;
import com.ocr.p9PatientRisk.proxies.PatientProxy;
import com.ocr.p9PatientRisk.util.DateProvider;
import com.ocr.p9PatientRisk.util.Utils;
import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class PatientRiskServiceTest {

    @Autowired
    private PatientRiskService patientRiskService;

    @MockBean
    private NoteProxy noteProxy;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private DateProvider dateProvider;

    @TestConfiguration
    static class PatientRiskServiceTestsContextConfiguration {

        @Bean
        public PatientRiskService PatientRiskService() {
            return new PatientRiskServiceImpl();
        }
    }

    @Test
    void getPatientRisk_1() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("12 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("M");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal" +
                ",Cholestérol,Vertige,Rechute,Réaction,Anticorps");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("2");
        noteDTO.setNote("Once upon a time 2...Hémoglobine A1C,Microalbumine,Cholestérol,Vertige...");
        noteDTO.setTitle("Titre de la note 2");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        assertTrue(patientRiskDTO.getRisk() != null);
        assertTrue(patientRiskDTO.getPatientInfo() != null);
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_2() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Aline");
        patient.setPhone("0102030405");
        patient.setSex("F");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal" +
                ",Cholestérol,Vertige,Rechute,Réaction,Anticorps");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("2");
        noteDTO.setNote("Once upon a time 2...Hémoglobine A1C,Microalbumine,Cholestérol,Vertige...");
        noteDTO.setTitle("Titre de la note 2");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        assertTrue(patientRiskDTO.getRisk().equals("Early onset"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Aline Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_3() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Aline");
        patient.setPhone("0102030405");
        patient.setSex("F");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        assertTrue(patientRiskDTO.getRisk().equals("None"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Aline Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_4() {
        LocalDate birth = LocalDate.of(1950, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Aline");
        patient.setPhone("0102030405");
        patient.setSex("F");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        assertTrue(patientRiskDTO.getRisk().equals("Borderline"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Aline Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_5() {
        LocalDate birth = LocalDate.of(1950, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("M");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        assertTrue(patientRiskDTO.getRisk().equals("In Danger"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_6() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("M");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        System.out.println("patientRiskDTO.getRisk()" + patientRiskDTO.getRisk());
        assertTrue(patientRiskDTO.getRisk().equals("In Danger"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_7() {
        LocalDate birth = LocalDate.of(1900, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("M");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        System.out.println("patientRiskDTO.getRisk()" + patientRiskDTO.getRisk());
        assertTrue(patientRiskDTO.getRisk().equals("In Danger"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_8() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("M");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal");
        noteDTO.setTitle("Titre de la note 1");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        System.out.println("patientRiskDTO.getRisk()" + patientRiskDTO.getRisk());
        assertTrue(patientRiskDTO.getRisk().equals("Early onset"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_9() {
        LocalDate birth = LocalDate.of(2000, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("F");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal" +
                ",Cholestérol,Vertige,Rechute,Réaction,Anticorps");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        System.out.println("patientRiskDTO.getRisk()" + patientRiskDTO.getRisk());
        assertTrue(patientRiskDTO.getRisk().equals("Early onset"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

    @Test
    void getPatientRisk_10() {
        LocalDate birth = LocalDate.of(1900, 1, 15);
        LocalDate dt2 = LocalDate.of(2020, 1, 15);
        PatientDTO patient = new PatientDTO();
        patient.setAddress("27 rue des oliviers");
        patient.setBirthDate(birth);
        patient.setFamilly("Martin");
        patient.setGiven("Alain");
        patient.setPhone("0102030405");
        patient.setSex("F");
        patient.setId(999);
        Mockito.when(patientProxy.getPatientById(999)).thenReturn(patient);

        List<NoteDTO> notes = new ArrayList<>();
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setPatientId(999);
        noteDTO.setNoteId("1");
        noteDTO.setNote("Once upon a time...Hémoglobine A1C,Microalbumine,Taille,Poids,Fumeur,Anormal" +
                ",Cholestérol,Vertige,Rechute,Réaction,Anticorps");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);
        Mockito.when(dateProvider.getNow()).thenReturn(dt2);

        PatientRiskDTO patientRiskDTO = patientRiskService.getPatientRisk(999);

        System.out.println("patientRiskDTO.getRisk()" + patientRiskDTO.getRisk());
        assertTrue(patientRiskDTO.getRisk().equals("Early onset"));
        assertTrue(patientRiskDTO.getPatientInfo().startsWith("Alain Martin"));
        assertTrue(patientRiskDTO.getCalculated() == true);
    }

}
