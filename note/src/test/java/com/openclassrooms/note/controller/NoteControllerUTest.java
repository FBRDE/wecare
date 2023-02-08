package com.openclassrooms.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.note.model.Note;
import com.openclassrooms.note.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerUTest {
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    NoteService noteService;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void getAllNotesTest() throws Exception {
        Note note=new Note("id_note",1,new Date(),"note_test");
        List<Note> notesList=new ArrayList<>();
        notesList.add(note);

        when(noteService.getAllNotes()).thenReturn(notesList);

        mockMvc.perform(get("/notes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andReturn();
    }

    @Test
    public void getNoteByIdTest() throws Exception {
        Note note=new Note("id_note",1,new Date(),"note_test");

        when(noteService.getNoteById("id_note")).thenReturn(note);

        mockMvc.perform(get("/note/id_note"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getNoteByPatientIdTest() throws Exception {
        Note note=new Note("id_note",1,new Date(),"note_test");
        List<Note> notes=new ArrayList<>();
        notes.add(note);
        when(noteService.getNoteByPatientId(1)).thenReturn(notes);

        mockMvc.perform(get("/note/patient/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addNoteTest() throws Exception {
        Note note=new Note("id_note",1,new Date(),"note_test");

        when(noteService.addNote(note)).thenReturn(note);
        mockMvc.perform(post("/note/add").content(mapper.writeValueAsString(note))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void updateNoteTest() throws Exception {
        Note note1=new Note("id_note",1,new Date(),"note_test");
        Note note2=new Note("id_note",1,new Date(),"note_test");
        when(noteService.updateNote(note1,"id_note")).thenReturn(note2);
        mockMvc.perform(put("/note/update/id_note").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(note1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void deleteNoteTest() throws Exception {
        Note note=new Note("id_note",1,new Date(),"note_test");
        when(noteService.deleteNote("id_note")).thenReturn(note);
        mockMvc.perform(delete("/note/delete/id_patient"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}

