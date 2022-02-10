package com.pwldata.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pwldata.domain.NoteDoc;

public interface NotesRepository extends MongoRepository<NoteDoc, String> {
}
