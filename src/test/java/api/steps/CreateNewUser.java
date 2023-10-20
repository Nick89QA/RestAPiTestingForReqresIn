package api.steps;

import api.pojo.PojoCreateUpdateUserRequest;
import api.pojo.PojoCreateUpdateUserResponse;
import api.specification.Specification;
import logger.MyLogger;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateNewUser {

    private static final Logger logger = MyLogger.getLogger();


    public static void createUser() {
        try {
            PojoCreateUpdateUserRequest rq = UserGenerator.createSimpleUser();

            PojoCreateUpdateUserResponse rs = given()
                    .spec(Specification.requestSpec())
                    .basePath(createUser)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then()
                    .spec(Specification.response201Created())
                    .extract().as(PojoCreateUpdateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoCreateUpdateUserResponse::getName, PojoCreateUpdateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());
            logger.info("The user has been created");

        } catch (Exception e) {
            logger.error("the error has happened when user was created" + e.getMessage());
        }

    }

    public static void createUserWrongPath() {
        PojoCreateUpdateUserRequest rq = UserGenerator.createSimpleUser();
        given()
                .basePath(createUserIncorrectPath)
                .body(rq)
                .log().body()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithWrongUrl() {
        PojoCreateUpdateUserRequest rq = UserGenerator.createSimpleUser();

        given()
                .basePath(createUser)
                .body(rq)
                .log().all()
                .when().post()
                .then().log().all();

    }

    public static void createUserWithMinimumCharBody() {
        try {
            PojoCreateUpdateUserRequest rq = UserGenerator.createUserWithMinCharacters();

            PojoCreateUpdateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoCreateUpdateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoCreateUpdateUserResponse::getName, PojoCreateUpdateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());


            logger.info("The user with min characters has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithMaximumCharBody() {
        try {
            PojoCreateUpdateUserRequest rq = UserGenerator.createUserWithMaxCharacters();

            PojoCreateUpdateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoCreateUpdateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoCreateUpdateUserResponse::getName, PojoCreateUpdateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());

            logger.info("The user with max characters body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }

    public static void createUserWithEmptyBody() {
        try {
            PojoCreateUpdateUserRequest rq = UserGenerator.createUserWithEmptyBrackets();

            PojoCreateUpdateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoCreateUpdateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoCreateUpdateUserResponse::getJob, PojoCreateUpdateUserResponse::getName)
                    .containsExactly(rq.getJob(), rq.getName());


            logger.info("The user with empty body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}

