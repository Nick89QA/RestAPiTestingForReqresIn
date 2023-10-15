package api.steps;

import api.pojo.CreateUserRequest;
import api.pojo.CreateUserResponse;
import logger.MyLogger;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NewUser {

    private static final Logger logger = MyLogger.getLogger();


    public static void createUser() {
        try {
            CreateUserRequest rq = UserGenerator.createSimpleUser();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName, CreateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());
            logger.info("The user has been created");

        } catch (Exception e) {
            logger.error("the error has happened when user was created" + e.getMessage());
        }

    }

    public static void createUserWrongPath() {
        CreateUserRequest rq = UserGenerator.createSimpleUser();
        given()
                .basePath(createUserIncorrectPath)
                .body(rq)
                .log().body()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithWrongUrl() {
        CreateUserRequest rq = UserGenerator.createSimpleUser();

        given()
                .basePath(createUser)
                .body(rq)
                .log().all()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithMinimumCharBody() {
        try {
            CreateUserRequest rq = UserGenerator.createUserWithMinCharacters();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName, CreateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());


            logger.info("The user with min characters has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithMaximumCharBody() {
        try {
            CreateUserRequest rq = UserGenerator.createUserWithMaxCharacters();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName, CreateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());

            logger.info("The user with max characters body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithEmptyBody() {
        try {
            CreateUserRequest rq = UserGenerator.createUserWithEmptyBrackets();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getJob, CreateUserResponse::getName)
                    .containsExactly(rq.getJob(), rq.getName());


            logger.info("The user with empty body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}

