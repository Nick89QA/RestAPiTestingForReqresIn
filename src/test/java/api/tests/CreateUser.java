package api.tests;

import api.pojo.CreateUserRequest;
import api.pojo.CreateUserResponse;
import api.specification.Specification;
import io.restassured.http.ContentType;
import logger.MyLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static api.endpoints.Endpoints.URL;
import static api.endpoints.Endpoints.createUser;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateUser {

    private static final Logger logger = MyLogger.getLogger();


    @Test
    public void createUser() {
        try {
            Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());

            CreateUserRequest rq = new CreateUserRequest();
            rq.setName("Nick");
            rq.setJob("Leader");

            CreateUserResponse rs = given()
                    .baseUri(URL)
                    .basePath(createUser)
                    .contentType(ContentType.JSON)
                    .body(rq)
                    .log().body()
                    .when().post()
                    .then().extract().as(CreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(CreateUserResponse::getName)
                    .isEqualTo(rq.getName());

        } catch (Exception e) {
            logger.error("the error has happened when user was created");
        }
        logger.info("The user has been created");
    }
}

