package com.pwldata.controllers;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.pwl.api.v1.model.NoteList;
import com.pwldata.config.ApiError;
import com.pwldata.repositories.NotesRepository;

import io.restassured.RestAssured;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
class NotesControllerExceptionTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.6")
            .withExposedPorts(27017)
            .waitingFor(Wait
                    .forLogMessage(".*Waiting for connections*.*", 1));

    @LocalServerPort
    int serverPort;


    @Autowired
    NotesRepository notesRepository;

    @AfterEach
    void cleanUp() {
        notesRepository.deleteAll();
    }

    @PostConstruct
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = serverPort;
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Test
    void getNodePositiveScenario() {
        when()
                .get("/notes")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .as(NoteList.class)
                .getItemList()
                .isEmpty();
    }

    @Test
    void getAddNoteMissingTitle() {
        given().when().get("/notes").then().statusCode(200);
    }


    @Test
    void addNoteReturnExceptionWhenMissingTitle() {
        given().queryParam("text", "cdcddc").when()
                .post("/notes")
                .then()
                .extract()
                .as(ApiError.class)
                .getStatus().is4xxClientError();
    }

    @Test
    void addNoteReturnExceptionWhenMissingText() {
        given().queryParam("title", "Title1").when()
                .post("/notes")
                .then()
                .extract()
                .as(ApiError.class)
                .getStatus().is4xxClientError();
    }

    @Test
    void addNoteReturnExceptionWhenInvalidTag() {
        given().queryParam("title", "Title1")
                .queryParam("text", "Title1")
                .queryParam("tag", "invalid value")
                .when()
                .post("/notes")
                .then()
                .extract()
                .as(ApiError.class)
                .getStatus().is4xxClientError();
    }

    @Test
    void addNoteReturnExceptionWhenEmptyText() {
        given().queryParam("title", "")
                .queryParam("text", "Title1")
                .queryParam("tag", "BUSINESS")
                .when()
                .post("/notes")
                .then()
                .extract()
                .as(ApiError.class)
                .getStatus().is4xxClientError();
    }

    @Test
    void addNoteReturnExceptionWhenEmptyTitle() {
        ApiError error = given().queryParam("title", "Title2")
                .queryParam("text", "")
                .queryParam("tag", "BUSINESS")
                .when()
                .post("/notes")
                .then()
                .extract()
                .as(ApiError.class);

        assertThat(error).isNotNull();
        assertThat(error.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(error.getMessage()).isEqualTo("text is empty");
        assertThat(error.getErrors()).asList().contains("Incorrect note parameters");
    }

    @Test
    void deleteReturnExceptionWhenIncorrectId() {
        String noteId = "Pawliszcze1";
        ApiError error = when()
                .delete("/notes/" + noteId)
                .then()
                .extract()
                .as(ApiError.class);

        assertThat(error).isNotNull();
        assertThat(error.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(error.getMessage()).isEqualTo("Note with id " + noteId + " not found");
        assertThat(error.getErrors()).asList().contains("Incorrect note parameters");
    }


}