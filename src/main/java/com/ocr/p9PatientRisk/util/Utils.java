package com.ocr.p9PatientRisk.util;

import com.ocr.p9PatientRisk.model.NoteDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utils Class
 */
public class Utils {

    private static final Logger log = LogManager.getLogger(Utils.class);

    public static Integer calculateAge(LocalDate birthDate, LocalDate currentDate) {
        log.debug("calculateAge");
        if (currentDate == null) {
            currentDate = new DateProvider().getNow();
        }
        if (birthDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        }
        return null;
    }

    public static Map<String, Integer> searchItems(String[] items, List<NoteDTO> notes) {
        log.debug("searchItems");
        Map<String, Integer> mapResult = new HashMap<>();

        for(NoteDTO note : notes) {
            for(String item : items) {
                if(note.getNote().toLowerCase().contains(item.toLowerCase())) {
                    if(mapResult.get(item) != null) {
                        mapResult.put(item,mapResult.get(item)+1);
                    } else {
                        mapResult.put(item,1);
                    }
                }
            }
        }
        return mapResult;
    }

}
