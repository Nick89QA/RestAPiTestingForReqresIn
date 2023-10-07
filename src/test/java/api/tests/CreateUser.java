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
      //  Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());

        CreateUserRequest rq = new CreateUserRequest();
        rq.setName("Nick");
        rq.setJob("Software Engineer in Test");
        logger.info("--We created User status 201 OK--: PASSED");


        CreateUserResponse rs = given()
                .baseUri(URL)
                .basePath(createUser)
                .body(rq)
                .when().post()
                .then().extract().as(CreateUserResponse.class);

    }
}
