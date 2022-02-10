package com.pwl.api;

import com.pwl.api.config.NoteApiClientConfig;
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.ApiResponse;
import com.pwl.client.v1.Note;
import com.pwl.client.v1.NoteList;
import com.pwl.client.v1.NotesApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
public class NotesApiPostTest extends NoteApiClientConfig {

    private ApiResponse<Note> noteApiResponse;

    private ApiException exception;


    @Given("I send a post request to add note with title: {string} , text: {string} , tag: {string}")
    public void addNote(String title, String text, String tag) {
        ApiClient notesApiClient = getClient();
        NotesApi apiInstance = new NotesApi(notesApiClient);
        try {

            noteApiResponse = apiInstance.addNoteWithHttpInfo(
                    title,
                    text,
                    tag);
            System.out.println(noteApiResponse.getData().toString());

        } catch (ApiException e) {
            log.error("Exception when calling NotesApi#addNote");
            log.error("Status code: " + e.getCode());
            log.error("Reason: " + e.getResponseBody());
            log.error("Response headers: " + e.getResponseHeaders());
            exception = e;
        }
    }

    @Then("Created response will contain notes with id and title: {string} , text: {string} , tag: {string}")
    public void createdResponseWillContainNotesWithIdAndTitleTextTag(String title, String text, String tag) {
        then(noteApiResponse).isNotNull();
        then(noteApiResponse.getData().getId()).isNotEmpty();
        then(noteApiResponse.getData().getText()).isEqualTo(text);
        then(noteApiResponse.getData().getTitle()).isEqualTo(title);
        then(noteApiResponse.getData().getTag()).isEqualTo(tag == null || "null".equals(tag) ? null : Note.TagEnum.fromValue(tag));
    }

    @Then("User will get exception with status code {int}")
    public void userWillGetException(int statusCode) {
        log.info("Check exception with code : " + statusCode);
        then(noteApiResponse).isNull();
        then(exception.getCode()).isEqualTo(statusCode);
    }


    @Then("I send a request to get notes with criteria only by id")
    public void iSendARequestToGetNotesWithCriteriaOnlyById() {
        then(noteApiResponse.getData()).isNotNull();
        then(noteApiResponse.getData().getId()).isNotEmpty();
        try {

            String noteId = noteApiResponse.getData().getId();

            ApiClient notesApiClient = getClient();
            NotesApi apiInstance = new NotesApi(notesApiClient);

            NoteList notes = apiInstance.getNotes(0, 100, noteId, null, null);

            then(notes).isNotNull();
            then(notes.getItemList()).isNotNull();
            then(notes.getItemList().size()).isEqualTo(1);

        } catch (Exception e) {
            assert false;

        }
    }
}