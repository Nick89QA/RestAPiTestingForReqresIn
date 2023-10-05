package api.tests;

import api.endpoints.Endpoints;
import api.specification.Specification;
import io.restassured.http.ContentType;
import logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static api.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;

public class SingleUser {


    private static final String getSingleUser = Endpoints.getSingleUser;
    private static final String incorrectUrl = Endpoints.incorrectUrl;
    private static final String incorrectPathGetSingleUser = Endpoints.incorrectPathGetSingleUser;
    private static final Logger logger = MyLogger.getLogger();

    @Test
    public void getSingleUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().body()
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
        logger.info("--We match email, first name,last name from single user: PASSED");

    }


    @Test
    public void checksStatusCodeWIthIncorrectUrl() {
        Specification.installSpecification(Specification.incorrectUrlRequestSpec(), Specification.response404NotFound());
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().all();
        logger.info("--Status 404, ContentType HTML : PASSED");

    }


    @Test
    public void checksStatusCodeWIthIncorrectPath() {
        given()
                .baseUri(URL)
                .basePath(incorrectPathGetSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all().statusCode(404);
        logger.info("--We checked status code with incorrect path 404 : PASSED");

    }

    /**
     * this test assert incorrect email in response
     */
    @Test
    public void assertIncorrectEmail() {
        given()
                .baseUri(URL)
                .basePath(getSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all()
                .body("data.email", not(equalTo("janet.weaverreqres.in")));
        logger.info("--We checked status code with incorrect path 404 : PASSED");


    }

    /**
     * this test assert incorrect first name in response
     */
    @Test
    public void assertIncorrectFirstName() {
        given()
                .baseUri(URL)
                .basePath(getSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all()
                .body("data.first_name", not(equalTo("Jant")));

    }
}
