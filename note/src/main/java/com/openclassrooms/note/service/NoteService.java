package com.openclassrooms.note.service;

import com.openclassrooms.note.model.Note;
import com.openclassrooms.note.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    public Note addNote(Note note)
    {
        return noteRepository.save(note);
    }
    public Note updateNote(Note note,String id)
    {
        Note savedNote=null;
        Optional<Note> noteToUpdate=noteRepository.findById(id);
        if(noteToUpdate.isPresent())
        {   noteToUpdate.get().setNote(note.getNote());

            noteToUpdate.get().setIdPatient(note.getIdPatient());
            noteToUpdate.get().setConsultationDate(note.getConsultationDate());
            savedNote= noteRepository.save(noteToUpdate.get());
        }
        return savedNote;
    }
    public Note deleteNote(String id)
    {
        Optional<Note> noteToDelete = noteRepository.findById(id);
        if(noteToDelete.isPresent())
        {
            noteRepository.deleteById(id);
            return noteToDelete.get();
        }
        else return null;
    }

    public Note getNoteById(String id) {
       return noteRepository.findById(id).get();
    }

    public List<Note> getNoteByPatientId(int id) {

       return noteRepository.findByIdPatient(id);
    }
}