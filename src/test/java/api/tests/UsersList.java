package api.tests;

import api.pojo.UserPojo;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.endpoints.Endpoints;

import java.util.List;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;

public class UsersList {

    private static final String getListUsers = Endpoints.getListUsers;
    private static final String URL = Endpoints.URL;

    /**
     * this method gets list of users and match email and name in JsonFile
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

    }

    /**
     * this method checks users and every user has avatar and id
     */
    @Test
    public void getListOfUsers() {
        List<UserPojo> users = given()
                .baseUri(URL)
                .basePath(getListUsers)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200).log().all().statusCode(200)
                .extract().jsonPath().getList("data", UserPojo.class);
        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        /**
         * this method checks that all emails ends on "@reqres.in"
         */
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        /**
         * this method checks that all avatars has format jpg
         */
        Assertions.assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("jpg")));
    }
}
