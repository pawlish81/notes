package com.pwldata.services;

import com.pwl.api.v1.model.Note;
import com.pwldata.domain.NoteDoc;
import com.pwldata.exceptions.NoteNotFoundException;
import com.pwldata.repositories.NotesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesService {
    private final NotesRepository notesRepository;


    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> getAllNotes() {

        List<NoteDoc> notes = notesRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
        return notes.stream().map(MapperService::noteDocToNote).collect(Collectors.toList());
    }

    public NoteDoc createNote(NoteDoc noteDoc) {
        return notesRepository.save(noteDoc);
    }

    public boolean noteExist(String id){
        return notesRepository.existsById(id);
    }

    public void deleteNote(String id) {
        notesRepository.deleteById(id);
    }

    public NoteDoc findNotesById(String id) {
        Optional<NoteDoc> note = notesRepository.findById(id);
        return note.orElseThrow(()-> new NoteNotFoundException("Note with id {0} not found"));
    }

    public NoteDoc update(NoteDoc noteDoc) {
        return  notesRepository.save(noteDoc);
    }
}
