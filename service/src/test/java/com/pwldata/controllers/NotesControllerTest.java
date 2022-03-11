//package com.pwldata.controllers;
//
//
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import javax.annotation.PostConstruct;
//
//import com.pwl.rocket_sim.api.v1.model.Rocket;
//import com.pwl.rocket_sim.api.v1.model.RocketList;
//import com.pwl.rocket_sim.api.v1.model.UpdateRocket;
//import com.pwldata.repositories.RocketRepository;
//import com.pwldata.services.RocketService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.containers.wait.strategy.Wait;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.pwl.api.v1.model.Note;
//import com.pwl.api.v1.model.NoteList;
//import com.pwl.api.v1.model.UpdatedNote;
//import com.pwl.api.v1.model.UpdatedNote.TagEnum;
//import com.pwldata.repositories.NotesRepository;
//
//import io.restassured.RestAssured;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import io.restassured.http.ContentType;
//
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@Testcontainers
//@DirtiesContext
//class NotesControllerTest {
//
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.6")
//            .waitingFor(Wait
//                    .forLogMessage(".*Waiting for connections*.*", 1));
//
//    @LocalServerPort
//    int serverPort;
//
//    @Autowired
//    RocketRepository rocketRepository;
//
//
//    @AfterEach
//    void cleanUp() {
//        rocketRepository.deleteAll();
//    }
//
//    @PostConstruct
//    void setup() {
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = serverPort;
//    }
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @Test
//    void getNotes() {
//
//        RocketList rocketList = given().queryParam("size", 1)
//                .queryParam("page", 10)
//                .when()
//                .get("/rockets")
//                .then()
//                .statusCode(200)
//                .and()
//                .extract()
//                .as(RocketList.class);
//
//        assertThat(rocketList).isNotNull();
//        assertThat(rocketList.getItemList()).isNotNull();
//
//    }
//
//    @Test
//    void updateNote() throws JsonProcessingException {
////        //given
////        Rocket rocket = given()
////                .queryParam("title", "Title1")
////                .queryParam("text", "Title1")
////                .queryParam("tag", "BUSINESS")
////                .when()
////                .post("/notes")
////                .then()
////                .statusCode(201)
////                .and()
////                .extract()
////                .as(Rocket.class);
////
////        assertThat(rocket).isNotNull();
////
////        UpdateRocket updatedNote = new UpdateRocket()
////                .id(rocket.getId())
////                .createDate(null)
////                .tag(TagEnum.IMPORTANT)
////                .text("testupdated")
////                .title("Title10");
////
////        Note noteUpdated = given()
////                .contentType(ContentType.JSON)
////                .body(new ObjectMapper().writeValueAsString(updatedNote))
////                .when()
////                .put("/notes")
////                .then()
////                .statusCode(200)
////                .and()
////                .extract()
////                .as(Note.class);
////
////        NoteList noteList = given()
////                .queryParam("size", 10)
////                .queryParam("page", 0)
////                .queryParam("id", noteUpdated.getId())
////                .when()
////                .get("/notes")
////                .then()
////                .statusCode(200)
////                .and()
////                .extract()
////                .as(NoteList.class);
////
////        assertThat(noteList).isNotNull();
////        assertThat(noteList.getItemList()).isNotNull();
////        assertThat(noteList.getItemList().size()).isEqualTo(1);
////        assertThat(noteList.getItemList()).asList().contains(noteUpdated);
//
//    }
//
//    @Test
//    void addNote() {
//        Rocket rocket = given().queryParam("title", "Title1")
//                .queryParam("text", "Title1")
//                .queryParam("tag", "BUSINESS")
//                .when()
//                .post("/notes")
//                .then()
//                .statusCode(201)
//                .and()
//                .extract()
//                .as(Rocket.class);
//        //then
//        assertThat(rocket).isNotNull();
//        assertThat(rocket.getId()).isNotNull();
//    }
////
////    @Test
////    void deleteNote() {
////        //given
////        Note note = given().queryParam("title", "Title1")
////                .queryParam("text", "Title1")
////                .queryParam("tag", "BUSINESS")
////                .when()
////                .post("/notes")
////                .then()
////                .statusCode(201)
////                .and()
////                .extract()
////                .as(Note.class);
////
////        when()
////                .delete("/notes/" + note.getId())
////                .then()
////                .statusCode(200);
////
////    }
//}