package com.openclassrooms.note.model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    // *******************************************************************


    @Test
    public void testGetNoteValues() {
        Note note = new Note("noteId", 1, new Date(), "note");
        assertEquals(1, note.getIdPatient());
        assertEquals("noteId", note.getId());
        assertEquals(new Date(), note.getConsultationDate());
        assertEquals("note", note.getNote());

    }


    // *******************************************************************



    @Test
    public void testSetNoteValues() {
        Note note = new Note("noteId", 1, new Date(), "note");
        note.setId("newId");
        note.setIdPatient(2);
        note.setNote("newNote");
        assertEquals(2, note.getIdPatient());
        assertEquals("newId", note.getId());
        assertEquals("newNote", note.getNote());
    }

}
