package com.ocr.p9PatientRisk.service;

import com.ocr.p9PatientRisk.model.NoteDTO;
import com.ocr.p9PatientRisk.model.PatientDTO;
import com.ocr.p9PatientRisk.model.PatientRiskDTO;
import com.ocr.p9PatientRisk.proxies.NoteProxy;
import com.ocr.p9PatientRisk.proxies.PatientProxy;
import com.ocr.p9PatientRisk.util.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


// TODO gérer not found

/**
 * PatientRiskServiceImpl : implementation for PatientRiskService
 */
@Service
public class PatientRiskServiceImpl implements PatientRiskService {

    @Autowired
    private PatientProxy patientProxy;

    @Autowired
    private NoteProxy noteProxy;

    public PatientRiskServiceImpl() {
    }

    private static final Logger log = LogManager.getLogger(PatientRiskServiceImpl.class);

    private static final String riskNone = "None";
    private static final String riskBorder = "Borderline";
    private static final String riskDanger = "In Danger";
    private static final String riskEarly = "Early onset";

    private static final String[] declencheurs = new String[]{"Hémoglobine A1C","'Microalbumine","Taille","Poids","Fumeur"
            ,"Anormal","Cholestérol","Vertige","Rechute","Réaction","Anticorps"};

    @Override
    public PatientRiskDTO getPatientRisk(Integer Id) {

        log.debug("getPatientRisk");

        PatientRiskDTO patientRiskDTO = new PatientRiskDTO();
        List<NoteDTO> notes = new ArrayList<>();

        PatientDTO patientDTO = patientProxy.getPatientById(Id);
        notes = noteProxy.getNotesByPatientId(Id);
        Map<String,Integer> mapResult;

        // TODO voir le cas de  = 30 ans si on entre dans les conditions

        if(patientDTO != null) {
            Integer agePatient = Utils.calculateAge(patientDTO.getBirthDate(), LocalDate.now());
            patientRiskDTO.setPatientId(patientDTO.getId());
            patientRiskDTO.setPatientInfo("A faire..., age ...:" + agePatient);
            // Cas 1
            if(notes.size() == 0) {
                patientRiskDTO.setCalculated(true);
                patientRiskDTO.setRisk(riskNone);
            } else {
                mapResult = Utils.searchItems(declencheurs, notes);
                if(patientDTO.getBirthDate() == null || patientDTO.getSex().equals("")) {
                    patientRiskDTO.setCalculated(false);
                    return patientRiskDTO;
                }
                if(agePatient > 30 && mapResult.size() == 2 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskBorder);
                }
                if(agePatient < 30 && patientDTO.getSex().equals("M") && mapResult.size() == 3 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskDanger);
                }
                if(agePatient < 30 && patientDTO.getSex().equals("F") && mapResult.size() == 4 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskDanger);
                }
                if(agePatient >= 30 && mapResult.size() == 6 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskDanger);
                }
                if(agePatient < 30 && patientDTO.getSex().equals("M") && mapResult.size() == 5 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskEarly);
                }
                if(agePatient < 30 && patientDTO.getSex().equals("F") && mapResult.size() == 7 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskEarly);
                }
                if(agePatient >= 30 && mapResult.size() > 8 ) {
                    patientRiskDTO.setCalculated(true);
                    patientRiskDTO.setRisk(riskEarly);
                }
            }
        }
        return patientRiskDTO;
    }

}
