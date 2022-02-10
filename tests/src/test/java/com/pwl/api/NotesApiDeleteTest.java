package com.pwl.api;

import com.pwl.api.config.NoteApiClientConfig;
import com.pwl.client.ApiException;
import com.pwl.client.v1.NoteList;
import com.pwl.client.v1.NotesApi;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotesApiDeleteTest extends NoteApiClientConfig {

    private NoteList notes;

    @Given("Database is empty")
    public void removeAllNotes() throws ApiException {
        NotesApi notesApi = new NotesApi(getClient());
        notes = notesApi.getNotes(0, 1000, null, null, null);
        assert notes.getItemList() != null;
        notes.getItemList().forEach(note -> removeNote(note.getId()));
        notes = notesApi.getNotes(0, 1000, null, null, null);

    }

    public void removeNote(String id) {
        try {
            NotesApi notesApi = new NotesApi(getClient());
            notesApi.deleteNote(id);
        } catch (ApiException e) {
            log.error("Exception when calling NotesApi#addNote");
            log.error("Status code: " + e.getCode());
            log.error("Reason: " + e.getResponseBody());
            log.error("Response headers: " + e.getResponseHeaders());
        }
    }

}
