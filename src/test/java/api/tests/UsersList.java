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

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.equalTo;

public class UsersList {

    private static final String getListUsers = Endpoints.getListUsers;
    private static final String getSingleUser = Endpoints.getSingleUser;
    private static final String URL = Endpoints.URL;
    private static final String incorrectUrl = Endpoints.incorrectUrl;
    private static final String incorrectPathGetSingleUser = Endpoints.incorrectPathGetSingleUser;

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

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        logger.info("--We match all emails which ends on @reqres.in--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("jpg")));
        logger.info("--We match all avatars and format is jpg--: PASSED");
    }

    /**
     * this test checks and asserts response from single user
     */
    @Test
    public void getSingleUser() {
        given()
                .baseUri(URL)
                .basePath(getSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().body().statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"));
        logger.info("--We match email, first name,last name from single user: PASSED");

    }

    /**
     * this test checks statusCode with incorrect url
     */
    @Test
    public void checksStatusCodeWIthIncorrectUrl() {
        given()
                .baseUri(incorrectUrl)
                .basePath(getSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all().statusCode(404);
    }

    /**
     * this test checks statusCode with incorrect base path
     */
    @Test
    public void checksStatusCodeWIthIncorrectPath() {
        given()
                .baseUri(URL)
                .basePath(incorrectPathGetSingleUser)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .log().all().statusCode(404);
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
                .body("data.email",not(equalTo("janet.weaverreqres.in")));

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
                .body("data.first_name",not(equalTo("Jant")));

    }


}