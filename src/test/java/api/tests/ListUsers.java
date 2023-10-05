package api.tests;

import api.pojo.UserPojo;
import api.specification.Specification;
import logger.MyLogger;

import io.restassured.http.ContentType;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.endpoints.Endpoints;
import org.slf4j.Logger;

import java.util.List;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ListUsers {

    private static final String getListUsers = Endpoints.getListUsers;

    private static final Logger logger = MyLogger.getLogger();

    @Test //positive
    public void getListOfUsersAndMatchEmailAndFirstName() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getListUsers)
                .when().get()
                .then().log().all()
                .body("data[1].email", equalTo("janet.weaver@reqres.in"))
                .body("data[0].first_name", equalTo("George"));
        logger.info("--We match email from 'id2' and name from 'id1' in the JSonFile--: PASSED");

    }

    @Test //positive
    public void getListOfUsers() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        List<UserPojo> users = given()
                .basePath(getListUsers)
                .when().get()
                .then().log().all()
                .extract().jsonPath().getList("data", UserPojo.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        logger.info("--We match all avatars with id--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        logger.info("--We match all emails which ends on @reqres.in--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("jpg")));
        logger.info("--We match all avatars and format is jpg--: PASSED");
    }

    @Test //positive
    public void checkNumberOfElementsInTheArray() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getListUsers)
                .when().get()
                .then().body("data.size", equalTo(6))
                .body("data.id", containsInAnyOrder(1, 2, 3, 4, 5, 6))
                .log().body();
        logger.info("--We match all id and numbers in the Array--: PASSED");

    }

    @Test //positive
    public void checkFieldAvatarInTheArray() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        given()
                .basePath(getListUsers)
                .when().get()
                .then().body("data.avatar", everyItem(notNullValue()))
                .log().body();
        logger.info("--We check field avatar not null--: PASSED");

    }

}