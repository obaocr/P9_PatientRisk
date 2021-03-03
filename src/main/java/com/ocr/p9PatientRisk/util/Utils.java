package com.ocr.p9PatientRisk.util;

import com.ocr.p9PatientRisk.model.NoteDTO;
import com.ocr.p9PatientRisk.service.PatientRiskServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private static final Logger log = LogManager.getLogger(Utils.class);

    public static Integer calculateAge(LocalDate birthDate) {
        // TODO  à faire
        log.debug("calculateAge");
        if(birthDate != null) {
            return 100;
        }
        return null;
    }

    public static Map<String,Integer> searchItems (String[] items, List<NoteDTO> notes) {
        // TODO  à faire
        log.debug("calculateAge");
        Map<String,Integer> mapResult = new HashMap<>();
        return mapResult;
    }
}
