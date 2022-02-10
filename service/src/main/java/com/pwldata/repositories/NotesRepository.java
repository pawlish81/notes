package com.pwldata.repositories;

import com.pwldata.domain.NoteDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<NoteDoc, String> {
}
