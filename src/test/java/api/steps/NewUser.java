package api.steps;

import api.pojo.PojoSingleUserRequest;
import api.pojo.PojoSingleUserResponse;
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
            PojoSingleUserRequest rq = UserGenerator.createSimpleUser();

            PojoSingleUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then().extract().as(PojoSingleUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoSingleUserResponse::getName, PojoSingleUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());
            logger.info("The user has been created");

        } catch (Exception e) {
            logger.error("the error has happened when user was created" + e.getMessage());
        }

    }

    public static void createUserWrongPath() {
        PojoSingleUserRequest rq = UserGenerator.createSimpleUser();
        given()
                .basePath(createUserIncorrectPath)
                .body(rq)
                .log().body()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithWrongUrl() {
        PojoSingleUserRequest rq = UserGenerator.createSimpleUser();

        given()
                .basePath(createUser)
                .body(rq)
                .log().all()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithMinimumCharBody() {
        try {
            PojoSingleUserRequest rq = UserGenerator.createUserWithMinCharacters();

            PojoSingleUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoSingleUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoSingleUserResponse::getName, PojoSingleUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());


            logger.info("The user with min characters has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithMaximumCharBody() {
        try {
            PojoSingleUserRequest rq = UserGenerator.createUserWithMaxCharacters();

            PojoSingleUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoSingleUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoSingleUserResponse::getName, PojoSingleUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());

            logger.info("The user with max characters body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithEmptyBody() {
        try {
            PojoSingleUserRequest rq = UserGenerator.createUserWithEmptyBrackets();

            PojoSingleUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoSingleUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoSingleUserResponse::getJob, PojoSingleUserResponse::getName)
                    .containsExactly(rq.getJob(), rq.getName());


            logger.info("The user with empty body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}

