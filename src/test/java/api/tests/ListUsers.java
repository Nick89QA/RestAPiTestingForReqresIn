package api.tests;

import api.pojo.UserPojo;
import logger.MyLogger;

import io.restassured.http.ContentType;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.endpoints.Endpoints;
import org.slf4j.Logger;

import java.util.List;


import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;

public class ListUsers {

    private static final String getListUsers = Endpoints.getListUsers;

    private static final String URL = Endpoints.URL;


    private static final Logger logger = MyLogger.getLogger();

    /**
     * this test checks and asserts from response email and name
     */
    @Test
    public void getListOfUsersAndMatchEmailAndFirstName() {
        given()
                .baseUri(URL)
                .basePath(getListUsers)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all().statusCode(200)
                .body("data[1].email", equalTo("janet.weaver@reqres.in"))
                .body("data[0].first_name", equalTo("George"));
        logger.info("--We match email from 'id2' and name from 'id1' in the JSonFile--: PASSED");

    }

    /**
     * this test asserts all the emails and avatars from response
     */
    @Test
    public void getListOfUsers() {
        List<UserPojo> users = given()
                .baseUri(URL)
                .basePath(getListUsers)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all().statusCode(200)
                .extract().jsonPath().getList("data", UserPojo.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        logger.info("--We match all avatars with id--: PASSED");


        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        logger.info("--We match all emails which ends on @reqres.in--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("jpg")));
        logger.info("--We match all avatars and format is jpg--: PASSED");
    }

    @Test
    public void checkNumberOfElementsInTheArray() {
        given()
                .baseUri(URL)
                .basePath(getListUsers)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .body("data.size", equalTo(6))
                .log().body().statusCode(200);
        logger.info("--We match number of elements in the array--: PASSED");

    }

   @Test
    public void checkFieldAvatarInTheArray() {
        given()
                .baseUri()
   }



}