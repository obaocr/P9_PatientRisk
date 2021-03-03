package com.ocr.p9PatientRisk.service;

import com.ocr.p9PatientRisk.model.NoteDTO;
import com.ocr.p9PatientRisk.model.PatientDTO;
import com.ocr.p9PatientRisk.proxies.NoteProxy;
import com.ocr.p9PatientRisk.proxies.PatientProxy;
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
        noteDTO.setNote("Once upon a time...");
        noteDTO.setTitle("Titre de la note");
        notes.add(noteDTO);
        Mockito.when(noteProxy.getNotesByPatientId(999)).thenReturn(notes);

        assertTrue(patientRiskService.getPatientRisk(999).getRisk() != null);
        assertTrue(patientRiskService.getPatientRisk(999).getPatientInfo() != null);
        assertTrue(patientRiskService.getPatientRisk(999).getCalculated() == true);
    }

}
