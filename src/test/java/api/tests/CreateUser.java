package api.tests;

import api.pojo.CreateUserRequest;
import api.pojo.CreateUserResponse;
import api.specification.Specification;
import io.restassured.http.ContentType;
import logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateUser {

    private static final Logger logger = MyLogger.getLogger();


    @Test
    public void createUser() {
        try {
            Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
            CreateUserRequest rq = UserGenerator.getSimpleUser();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName)
                    .isEqualTo(rq.getName());

        } catch (Exception e) {
            logger.error("the error has happened when user was created");
        }
        logger.info("The user has been created");
    }

    @Test
    public void createUserWithIncorrectPath() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response404NotFound());
        CreateUserRequest rq = UserGenerator.getSimpleUser();
                 given()
                .basePath(createUserIncorrectPath)
                .body(rq)
                .log().body()
                .when().post()
                .then().log().all();

    }

    @Test
    public void createUserWithIncorrectUrl() {
        Specification.installSpecification(Specification.requestSpecIncorrectUrl(), Specification.response404NotFound());
        CreateUserRequest rq = UserGenerator.getSimpleUser();

        given()
                .basePath(createUser)
                .body(rq)
                .log().all()
                .when().post()
                .then().log().all();

    }
}

