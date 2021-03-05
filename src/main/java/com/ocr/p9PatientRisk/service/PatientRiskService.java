package com.ocr.p9PatientRisk.service;

import com.ocr.p9PatientRisk.model.PatientRiskDTO;

import java.util.List;

/**
 * PatientRiskService Interface
 */
public interface PatientRiskService {

    public PatientRiskDTO getPatientRisk(Integer Id);
    public List<PatientRiskDTO> getPatientRiskByFamilly(String familly);

}

