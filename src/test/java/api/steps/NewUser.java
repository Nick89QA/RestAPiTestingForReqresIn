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
                    .extracting(CreateUserResponse::getName)
                    .isEqualTo(rq.getName());
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

    public static void createUserWithIncorrectParams() {
        try {
            CreateUserRequest rq = UserGenerator.createUserWithDiffParams();

            CreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName)
                    .isEqualTo(rq.getName());
            logger.info("The user has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}

