package com.ocr.p9PatientRisk.controller;

import com.ocr.p9PatientRisk.model.PatientRiskDTO;
import com.ocr.p9PatientRisk.service.PatientRiskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientRiskControllerTest {

    @MockBean
    private PatientRiskService patientRiskService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeShouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPatientRiskByIdShouldReturnOK() throws Exception {
        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(999);
        patientRiskDTO.setPatientInfo("Alain Martin (age 12)");
        patientRiskDTO.setRisk("In Danger");

        Mockito.when(patientRiskService.getPatientRisk(999)).thenReturn(patientRiskDTO);

        this.mockMvc.perform(get("/assess/999")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPatientRiskByIdShouldReturn_BadRequest() throws Exception {
        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(999);
        patientRiskDTO.setPatientInfo("Alain Martin (age 12)");
        patientRiskDTO.setRisk("In Danger");

        Mockito.when(patientRiskService.getPatientRisk(999)).thenReturn(patientRiskDTO);

        this.mockMvc.perform(get("/assess/0")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void getPatientRiskByNameShouldReturnOK() throws Exception {
        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        patientRiskDTO.setPatientId(999);
        patientRiskDTO.setPatientInfo("Alain Martin (age 12)");
        patientRiskDTO.setRisk("In Danger");

        Mockito.when(patientRiskService.getPatientRisk(999)).thenReturn(patientRiskDTO);

        this.mockMvc.perform(get("/assess")
                .param("familly","Martin")
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
