package com.ocr.p9PatientRisk.proxies;

import com.ocr.p9PatientRisk.model.NoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "microservice-note", url = "http://localhost:8049")
public interface NoteProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/notes/patient/{Id}")
    List<NoteDTO> getNotesByPatientId(@PathVariable("Id") Integer Id);

}