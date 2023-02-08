package com.openclassrooms.note.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.openclassrooms.note.model.Note;

import com.openclassrooms.note.service.NoteService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"${wecare.front.cross.origin}", "${report.cross}"})

@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteController {

    @Autowired
    NoteService noteService;

    private static final Logger logger = LogManager.getLogger("NoteController");

    @GetMapping(value="/notes")
    public List<Note> getAllNotes( ){
        return noteService.getAllNotes();
    }

    @GetMapping(value="/note/{id}")
    public Note getNoteById(@PathVariable("id") String id ){
        return noteService.getNoteById(id);
    }

    @GetMapping(value="/note/patient/{id}")
    public List<Note> getNoteByPatientId(@PathVariable("id") int id ){
        return noteService.getNoteByPatientId(id);
    }

    @PostMapping(value="/note/add")
    public void addNote(@RequestBody Note note)  {
        noteService.addNote(note);
    }
    @PutMapping(value="/note/update/{id}")
    public void updateNote(@RequestBody Note note,@PathVariable("id") String id)  {
        noteService.updateNote(note,id);
    }
    @DeleteMapping(value="/note/delete/{id}")
    public void deleteNote(@PathVariable("id") String id)  {
        noteService.deleteNote(id);
    }
}








