package com.pwldata.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pwl.rocket_sim.api.v1.model.NewRocket;
import com.pwl.rocket_sim.api.v1.model.RocketList;
import com.pwl.rocket_sim.api.v1.model.RocketType;
import com.pwldata.commons.AuthenticationConfig;
import com.pwldata.commons.TestObjectGenerator;
import com.pwldata.repositories.RocketRepository;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.swagger.v3.core.jackson.SwaggerModule;
import io.swagger.v3.core.util.DeserializationModule;
import org.exparity.hamcrest.date.DateMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
class RocketControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.6")
            .waitingFor(Wait
                    .forLogMessage(".*Waiting for connections*.*", 1));

    private ObjectMapper objectMapper = new ObjectMapper();

    @LocalServerPort
    int serverPort;

    @Autowired
    RocketRepository rocketRepository;

    String apiPath = "/rockets";

    @AfterEach
    void cleanUp() {
        rocketRepository.deleteAll();
    }

    @PostConstruct
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = serverPort;

        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void addRocket() throws JsonProcessingException {

        NewRocket newRocket = TestObjectGenerator.generateObject(NewRocket.class);

        Object buildDateString = given()
                .auth().basic(AuthenticationConfig.API_USER, AuthenticationConfig.API_PASSWORD)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(newRocket))
                .when()
                .post(apiPath)
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("id", notNullValue())
                .body("base", equalTo(newRocket.getBase()))
                .body("name", equalTo(newRocket.getName()))
                .body("desc", equalTo(newRocket.getDesc()))
                .body("company", equalTo(newRocket.getCompany()))
                .body("rocketType", equalTo(newRocket.getRocketType().toString()))
                .body("audit.createdByUser", equalTo(AuthenticationConfig.API_USER))
                .body("audit.modifiedByUser", equalTo(AuthenticationConfig.API_USER))
                .body("audit.createdDate", notNullValue())
                .body("audit.modifiedDate", notNullValue())
                .extract()
                .path("buildDate");

        OffsetDateTime buildDate = OffsetDateTime.parse(buildDateString.toString());
        assertThat(buildDate).isEqualToIgnoringNanos(newRocket.getBuildDate());


    }

    @Test
    void deleteRocket() {
    }

    @Test
    void getRockets() {

        RocketList rocketList = given().auth().basic(AuthenticationConfig.API_USER, AuthenticationConfig.API_PASSWORD)
                .queryParam("size", 1)
                .queryParam("page", 10)
                .when()
                .get(apiPath)
                .then()
                .statusCode(200)
                .and()
                .extract()
                .as(RocketList.class);

        assertThat(rocketList).isNotNull();
        assertThat(rocketList.getItemList()).isNotNull();

    }

    @Test
    void updateRocket() {
    }
}