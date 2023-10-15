package api.steps;

import api.specification.Specification;
import logger.MyLogger;
import org.slf4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static api.endpoints.Endpoints.getSingleUser;
import static api.endpoints.Endpoints.incorrectPathGetSingleUser;


public class SingleUser {


    private static final Logger logger = MyLogger.getLogger();

    public static void getSingleUser() {
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

        logger.info("--We match: id, email, first_name,last_name from the response: PASSED");

    }


    public static void checksStatusCodeWIthIncorrectUrl() {
        given()
                .basePath(getSingleUser)
                .when().get()
                .then()
                .log().all();
        logger.info("--Status 404, ContentType HTML : PASSED");

    }


    public static void checksStatusCodeWIthIncorrectPath() {
        given()
                .basePath(incorrectPathGetSingleUser)
                .when().get()
                .then()
                .log().body();
        logger.info("--Status 404, ContentType HTML : PASSED");

    }

}
