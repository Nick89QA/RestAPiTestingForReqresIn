package api.steps;

import api.pojo.PojoListOfUsers;
import io.cucumber.java.en.Given;
import logger.MyLogger;
import org.slf4j.Logger;

import java.util.List;

import static api.endpoints.Endpoints.getListUsers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;

public class ListOfUsers {
    private static final Logger logger = MyLogger.getLogger();


    public static void getUsers() {
        given()
                .basePath(getListUsers)
                .when().get()
                .then().log().all()
                .body("data[1].email", equalTo("janet.weaver@reqres.in"))
                .body("data[0].first_name", equalTo("George"));
        logger.info("--We match email from 'id2' and name from 'id1' in the JSonFile--: PASSED");
    }


    public static List<PojoListOfUsers> getAllUsers() {
        return given()
                .basePath(getListUsers)
                .when().get()
                .then().log().all()
                .extract().jsonPath().getList("data", PojoListOfUsers.class);

    }


    public static void checkAllNumberInArray() {
        given()
                .basePath(getListUsers)
                .when().get()
                .then().body("data.size", equalTo(6))
                .body("data.id", containsInAnyOrder(1, 2, 3, 4, 5, 6))
                .log().body();
        logger.info("--We match all id and numbers in the Array--: PASSED");
    }


    public static void checkTheFieldAvatar() {
        given()
                .basePath(getListUsers)
                .when().get()
                .then().body("data.avatar", everyItem(notNullValue()))
                .log().body();
        logger.info("--We check field avatar not null--: PASSED");
    }
}
