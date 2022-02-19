package com.pwldata.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pwldata.domain.NoteDoc;
import com.pwldata.exceptions.NoteNotFoundException;
import com.pwldata.repositories.NotesRepository;

@Service
public class NotesService {

    private final NotesRepository notesRepository;


    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public NoteDoc createNote(NoteDoc noteDoc) {
        return notesRepository.save(noteDoc);
    }


    public void deleteNote(String id) {
        notesRepository.deleteById(id);
    }

    public NoteDoc findNotesById(String id) {
        Optional<NoteDoc> note = notesRepository.findById(id);
        return note.orElseThrow(() -> new NoteNotFoundException("Note with id %s not found", id));
    }

    public NoteDoc update(NoteDoc noteDoc) {
        return notesRepository.save(noteDoc);
    }

    public Page<NoteDoc> findAll(NoteDoc noteDoc, Pageable paging) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", exact())
                .withMatcher("tag", exact())
                .withMatcher("title", exact());

        Example<NoteDoc> filter = Example.of(noteDoc, matcher);
        return notesRepository.findAll(filter, paging);

    }
}
