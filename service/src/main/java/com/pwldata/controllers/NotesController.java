package com.pwldata.controllers;


import java.time.LocalDateTime;
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
                .setId(id)
                .setTagFromString(tag);

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
    public ResponseEntity<Note> updateNote(UpdatedNote updatedNote) {
        NoteDoc noteDoc = notesService.findNotesById(updatedNote.getId());
        noteDoc.setText(updatedNote.getText());
        noteDoc.setTitle(noteDoc.getTitle());
        noteDoc.setTag(noteDoc.getTag());

        noteDoc.setTitle(Optional.of(updatedNote.getTitle()).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("title is empty")));

        noteDoc.setText(Optional.
                of(updatedNote.getText()).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("text is empty")));

        noteDoc.setCreateDate(updatedNote.getCreateDate() != null ? updatedNote.getCreateDate() : noteDoc.getCreateDate());

        Note note = NoteMapper.noteDocToNote(notesService.update(noteDoc));

        return ResponseEntity.ok(note);
    }

    @Override
    public ResponseEntity<Note> addNote(String title, String text, String tag) {
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.setText(text);
        noteDoc.setTitle(text);
        noteDoc.setTagFromString(tag);
        noteDoc.setTitle(Optional.ofNullable(title).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("title is empty")));

        noteDoc.setText(Optional.ofNullable(text).filter(Strings::isNotEmpty)
                .orElseThrow(() -> new NoteValidationException("text is empty")));
        noteDoc.setCreateDate(LocalDateTime.now());
        NoteDoc note = notesService.createNote(noteDoc);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(NoteMapper.noteDocToNote(note));
    }

    @Override
    public ResponseEntity<Void> deleteNote(String id) {
        notesService.findNotesById(id);
        notesService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
