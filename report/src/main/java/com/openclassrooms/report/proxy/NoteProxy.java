package com.openclassrooms.report.proxy;

import com.openclassrooms.report.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(value = "note", url = "${PROXY_NOTE:http://localhost:8082}",decode404 = true)
public interface NoteProxy {

    @GetMapping(value = "/note/patient/{id}")
    List<Note> getNoteByPatientId(@PathVariable("id") int id );

}
