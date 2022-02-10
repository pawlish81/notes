package com.pwldata.controllers;


import com.pwl.api.v1.NotesApi;
import com.pwl.api.v1.model.Note;
import com.pwl.api.v1.model.NoteList;
import com.pwl.api.v1.model.UpdatedNote;
import com.pwldata.domain.NoteDoc;
import com.pwldata.exceptions.NoteValidationException;
import com.pwldata.repositories.NotesRepository;
import com.pwldata.services.MapperService;
import com.pwldata.services.NotesService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@RestController()
public class NotesController implements NotesApi {

    private final NotesRepository notesRepository;

    private final NotesService notesService;

    public NotesController(NotesRepository notesRepository, NotesService notesService) {
        this.notesRepository = notesRepository;
        this.notesService = notesService;
    }


    @Override
    public ResponseEntity<NoteList> getNotes(Integer page, Integer size, String id, String title, String tag) {
        Pageable paging = PageRequest.of(page, size);


        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", exact())
                .withMatcher("tag", exact())
                .withMatcher("title", exact());

        NoteDoc noteDoc = new NoteDoc()
                .setTitle(title)
                .setId(id);

        if (Strings.isNotEmpty(tag)) {
            noteDoc.setTag(Note.TagEnum.fromValue(tag));
        }

        Example<NoteDoc> filter = Example.of(noteDoc, matcher);
        Page<NoteDoc> result = notesRepository.findAll(filter, paging);

        NoteList noteList = new NoteList();
        noteList.setItemList(result.toList()
                .stream()
                .sorted(Comparator.comparing(NoteDoc::getCreateDate))
                .map(MapperService::noteDocToNote)
                .collect(Collectors.toList()));
        noteList.setCurrentPage(result.getNumber());
        noteList.setTotalItems(result.getTotalElements());
        noteList.setTotalPage(result.getTotalPages());
        return ResponseEntity.ok(noteList);

    }

    @Override
    public ResponseEntity<Note> updateNote(UpdatedNote note) {
        NoteDoc noteDoc = notesService.findNotesById(note.getId());

        noteDoc.setTitle((note.getTitle() == null) ?
                noteDoc.getTitle() :
                Optional.
                        of(note.getTitle()).filter(Strings::isNotEmpty)
                        .orElseThrow(() -> new NoteValidationException("title is empty")));

        noteDoc.setText((note.getText() == null) ?
                noteDoc.getText() :
                Optional.
                        of(note.getText()).filter(Strings::isNotEmpty)
                        .orElseThrow(() -> new NoteValidationException("text is empty")));

        noteDoc.setCreateDate(note.getCreateDate() != null ? note.getCreateDate().toLocalDateTime() : noteDoc.getCreateDate());
        noteDoc.setTag(note.getTag() == null ? null : Note.TagEnum.fromValue(note.getTag().getValue()));
        return ResponseEntity.ok(MapperService.noteDocToNote(notesService.update(noteDoc)));
    }

    @Override
    public ResponseEntity<Note> addNote(String title, String text, String tag) {
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.setTitle(Optional.ofNullable(title).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("title is empty")));

        noteDoc.setText(Optional.ofNullable(text).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("text is empty")));
        noteDoc.setCreateDate(LocalDateTime.now());
        try {
            noteDoc.setTag(tag == null ? null : Note.TagEnum.fromValue(tag));
        } catch (IllegalArgumentException e) {
            throw new NoteValidationException("tag has incorrect value :" + tag + ", correct one is : " + Arrays.toString(Note.TagEnum.values()));
        }

        NoteDoc note = notesService.createNote(noteDoc);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MapperService.noteDocToNote(note));
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        notesService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
