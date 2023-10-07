package api.tests;

import api.pojo.CreateUserRequest;
import api.pojo.CreateUserResponse;
import api.specification.Specification;
import org.junit.jupiter.api.Test;

import static api.endpoints.Endpoints.URL;
import static io.restassured.RestAssured.given;

public class CreateUser {



    @Test
    public void createUser() {
        CreateUserRequest rq = new CreateUserRequest();
        rq.setName("Nick");
        rq.setJob("Software Engineer in Test");

        CreateUserResponse rs = given()
                .baseUri(URL)

    }
}
