package com.pwldata.controllers;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pwl.api.v1.NotesApi;
import com.pwl.api.v1.model.Note;
import com.pwl.api.v1.model.NoteList;
import com.pwl.api.v1.model.UpdatedNote;
import com.pwldata.common.NoteMapper;
import com.pwldata.domain.NoteDoc;
import com.pwldata.exceptions.NoteValidationException;
import com.pwldata.services.NotesService;

@RestController()
public class NotesController implements NotesApi {


    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }


    @Override
    public ResponseEntity<NoteList> getNotes(Integer page, Integer size, String id, String title, String tag) {

        Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        NoteDoc noteDoc = new NoteDoc()
                .setTitle(title)
                .setId(id);

        if (Strings.isNotEmpty(tag)) {
            noteDoc.setTag(Note.TagEnum.fromValue(tag));
        }

        Page<NoteDoc> result = notesService.findAll(noteDoc, paging);

        NoteList noteList = new NoteList();
        noteList.setItemList(result.toList()
                .stream()
                .map(NoteMapper::noteDocToNote)
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

        return ResponseEntity.ok(NoteMapper.noteDocToNote(notesService.update(noteDoc)));
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
                .body(NoteMapper.noteDocToNote(note));
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        notesService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
