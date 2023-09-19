package steps;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.*;

public class GetUsersList {

    private static final String getListUsers = Endpoints.getListUsers;

    @Test
    public void getUsers() {
     when().
             get("https://reqres.in/" + getListUsers).then()
                .log().body().statusCode(200);


    }

}
