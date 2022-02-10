package com.pwl.api;

import com.pwl.api.config.NoteApiClientConfig;
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.ApiResponse;
import com.pwl.client.v1.Note;
import com.pwl.client.v1.NoteList;
import com.pwl.client.v1.NotesApi;
import com.pwl.client.v1.UpdatedNote;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
public class NotesApiPutTest extends NoteApiClientConfig {

    private ApiResponse<Note> noteApiResponse;

    private ApiException exception;


    @Then("Updated response will contain notes with id and title: {string} , text: {string} , tag: {string}")
    public void updatedResponseWillContainNotesWithIdAndTitleTextTag(String title, String text, String tag) {
        then(noteApiResponse).isNotNull();
        then(noteApiResponse.getData().getId()).isNotEmpty();
        then(noteApiResponse.getData().getText()).isEqualTo(text);
        then(noteApiResponse.getData().getTitle()).isEqualTo(title);
        then(noteApiResponse.getData().getTag()).isEqualTo(tag == null || "null".equals(tag) ? null : Note.TagEnum.fromValue(tag));
    }

    @Given("I send a put request to update note with title: {string} , text: {string} , tag: {string}")
    public void iSendAPutRequestToUpdateNoteWithTitleTextTag(String title, String text, String tag) {
        ApiClient notesApiClient = getClient();
        NotesApi apiInstance = new NotesApi(notesApiClient);
        try {

            NoteList notes = apiInstance.getNotes(0, 1, null, null, null);
            assert !notes.getItemList().isEmpty();
            Optional<Note> anyNote = notes.getItemList().stream().findAny();
            if (anyNote.isPresent()) {
                UpdatedNote noteToUpdate = new UpdatedNote()
                        .id(anyNote.get().getId())
                        .tag(tag == null || "null".equals(tag) ? null : UpdatedNote.TagEnum.valueOf(tag))
                        .text(text)
                        .title(title);

                noteApiResponse = apiInstance.updateNoteWithHttpInfo(noteToUpdate);
            }


        } catch (ApiException e) {
            log.error("Exception when calling NotesApi#addNote");
            log.error("Status code: " + e.getCode());
            log.error("Reason: " + e.getResponseBody());
            log.error("Response headers: " + e.getResponseHeaders());
            exception = e;
        }
    }

    @And("I send a request to get note with new values title:{string} , text: {string} , tag: {string} by get endpoint")
    public void iSendARequestToGetNoteWithNewValuesTextTagByGetEndpoint(String title, String text, String tag) {
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

            Note note = notes.getItemList().get(0);

            then(note).isNotNull();
            then(note.getTitle()).isEqualTo(title);
            then(note.getText()).isEqualTo(text);
            if (note.getTag() != null) {
                then(note.getTag().getValue()).isEqualTo(tag);
            }

        } catch (Exception e) {
            assert false;

        }
    }
}