package com.ocr.p9PatientRisk.util;

import com.ocr.p9PatientRisk.model.NoteDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private static final Logger log = LogManager.getLogger(Utils.class);

    public static Integer calculateAge(LocalDate birthDate, LocalDate currentDate) {
        log.debug("calculateAge");
        if (currentDate == null) {
            currentDate = LocalDate.now();
        }
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        }
        return null;
    }

    public static Map<String, Integer> searchItems(String[] items, List<NoteDTO> notes) {
        // TODO  à faire
        log.debug("searchItems");
        Map<String, Integer> mapResult = new HashMap<>();
        return mapResult;
    }
}
