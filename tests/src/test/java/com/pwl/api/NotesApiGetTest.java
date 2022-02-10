package com.pwl.api;

import com.pwl.api.config.NoteApiClientConfig;
import com.pwl.client.ApiException;
import com.pwl.client.v1.NoteList;
import com.pwl.client.v1.NotesApi;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
public class NotesApiGetTest extends NoteApiClientConfig {

    private NoteList notes = new NoteList();

    @When("I send a request to get notes with criteria page:{int}, size:{int},id:{string},title:{string},tag:{string}")
    public void getAllNotes(int page, int size, String id, String title, String tag) {
        NotesApi notesApi = new NotesApi(getClient());
        try {
            notes = notesApi.getNotes(page, size, null, title, tag);

        } catch (ApiException e) {
            log.error("Exception when calling NotesApi#addNote");
            log.error("Status code: " + e.getCode());
            log.error("Reason: " + e.getResponseBody());
            log.error("Response headers: " + e.getResponseHeaders());
        }
    }


    @Then("the response will return list of notes with size {int}")
    public void theResponseWillReturnListOfNotesWithSize(int size) {
        then(notes).isNotNull();
        then(notes.getItemList()).isNotNull();
    }
}
