package api.tests;

import api.endpoints.Endpoints;
import api.pojo.UserPojo;
import api.specification.Specification;
import io.restassured.http.ContentType;
import logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;

import static api.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;

public class SingleUser {


    private static final String getSingleUser = Endpoints.getSingleUser;
    private static final String incorrectPathGetSingleUser = Endpoints.incorrectPathGetSingleUser;
    private static final Logger logger = MyLogger.getLogger();

    @Test  //positive
    public void getSingleUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().body()
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));

        logger.info("--We match: id, email, first_name,last_name from single user: PASSED");

    }


    @Test  //negative
    public void checksStatusCodeWIthIncorrectUrl() {
        Specification.installSpecification(Specification.incorrectUrlRequestSpec(), Specification.response404NotFound());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().all();
        logger.info("--Status 404, ContentType HTML : PASSED");

    }


    @Test  //negative
    public void checksStatusCodeWIthIncorrectPath() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response404NotFound());
        given()
                .basePath(incorrectPathGetSingleUser)
                .when().get()
                .then()
                .log().body();
        logger.info("--Status 404, ContentType HTML : PASSED");

    }

    /**
     * this test assert incorrect email in response
     */
    @Test  //negative
    public void assertIncorrectEmail() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().all()
                .body("data.email", not(equalTo("janet.weaverreqres.in")));
        logger.info("-- assert incorrect email in single user : PASSED");


    }

    /**
     * this test assert incorrect first name in response
     */
    @Test   //negative
    public void assertIncorrectFirstName() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().all()
                .body("data.first_name", not(equalTo("Jant")));

    }

}
