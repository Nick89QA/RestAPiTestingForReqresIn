package api.tests;

import api.pojo.CreateUserRequest;
import api.pojo.CreateUserResponse;
import api.specification.Specification;
import logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static api.endpoints.Endpoints.URL;
import static api.endpoints.Endpoints.createUser;
import static io.restassured.RestAssured.given;

public class CreateUser {

    private static final Logger logger = MyLogger.getLogger();


    @Test
    public void createUser() {

        try {
            Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());

            CreateUserRequest rq = new CreateUserRequest();
            rq.setName("Nick");
            rq.setJob("Software Engineer in Test");

            CreateUserResponse rs = given()
                    .baseUri(URL)
                    .basePath(createUser)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then().extract().as(CreateUserResponse.class);

        } catch (Exception e) {
            logger.error("the error has happened when user was created");
        }

    }
}
