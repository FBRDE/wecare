package com.openclassrooms.note.service;

import com.openclassrooms.note.model.Note;
import com.openclassrooms.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class NoteServiceUTest {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void getAllNotesTest() {
        Note note=new Note("id_note",1,new Date(),"note_test");
        List<Note> notesList=new ArrayList<>();
        notesList.add(note);

        when(noteRepository.findAll()).thenReturn(notesList);
        assertEquals(noteService.getAllNotes(),notesList);

    }
    @Test
    public void addNoteTest()
    {
        Note noteToSave = new Note("id_note",1,new Date(),"note_test");
        when(noteRepository.save(noteToSave)).thenReturn(noteToSave);

        Note noteActual = noteService.addNote(noteToSave);
        assertEquals(noteActual,noteToSave);

    }
    @Test
    public void updateNoteTest()
    {
        Note noteToUpdate = new Note("id_note",1,new Date(),"note_test");

        doReturn(Optional.of(noteToUpdate)).when(noteRepository).findById("id_note");
        when(noteRepository.save(noteToUpdate)).thenReturn(noteToUpdate);
        Note noteActual = noteService.updateNote(noteToUpdate,"id_note");
        assertEquals(noteActual,noteToUpdate);
    }

    @Test
    public void deleteNoteTest()
    {
        Note noteToDelete = new Note("id_note",1,new Date(),"note_test");

        doReturn(Optional.of(noteToDelete)).when(noteRepository).findById("id_note");
        doNothing().when(noteRepository).deleteById("id_note");
        Note noteActual = noteService.deleteNote("id_note");
        assertEquals(noteActual,noteToDelete);
    }

    @Test
    public void getNoteByIdTest()
    {
        Note note = new Note("id_note",1,new Date(),"note_test");
        when(noteRepository.findById("id_note")).thenReturn(Optional.of(note));
        Note noteActual = noteService.getNoteById("id_note");
        assertEquals(noteActual,note);
    }
}

