package com.openclassrooms.note.repository;

import com.openclassrooms.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface NoteRepository extends MongoRepository<Note, String> {

    @Override
    Optional<Note> findById(String s);

    List<Note> findByIdPatient(int id);
}