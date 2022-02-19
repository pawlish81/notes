package com.pwldata.repositories;


import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.pwldata.commons.TestObjectGenerator;
import com.pwldata.domain.NoteDoc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
public class NotesRepositoryTest {


    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.6")
            .waitingFor(Wait
                    .forLogMessage(".*Waiting for connections*.*", 1));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private NotesRepository notesRepository;

    @AfterEach
    void cleanUp() {
        this.notesRepository.deleteAll();
    }

    @Test
    void shouldReturnListOfNotes() {
        //given
        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);
        notesRepository.save(note);

        //when
        List<NoteDoc> resultList = notesRepository.findAll();

        //then
        assertEquals(1, resultList.size());
    }

    @Test
    void shouldReturnSavedNoteWithId() {
        //given
        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);

        //when
        NoteDoc result = notesRepository.save(note);

        //then
        assertThat(note.getId()).isNotEmpty();
        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(note);
    }

    @Test
    void shouldReturnUpdatedNote() {
        //given
        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);
        NoteDoc savedNote = notesRepository.save(note);
        //when
        savedNote.setText("One to Two and one text");
        NoteDoc updatedNote = notesRepository.save(note);

        //then
        assertThat(updatedNote).isNotNull();
        assertThat(updatedNote)
                .usingRecursiveComparison()
                .isEqualTo(savedNote);
    }

    @Test
    void shouldDeleteNote() {
        //given
        NoteDoc note = TestObjectGenerator.generateObject(NoteDoc.class);
        NoteDoc savedNote = notesRepository.save(note);
        //when
        notesRepository.delete(savedNote);

        //then
        Optional<NoteDoc> result = notesRepository.findById(note.getId());
        assertThat(result.isEmpty()).isTrue();
    }


}