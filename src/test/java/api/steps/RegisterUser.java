package api.steps;

import api.pojo.PojoCreateUpdateUserRequest;
import api.pojo.PojoCreateUserResponse;
import api.specification.Specification;
import logger.MyLogger;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.createUser;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterUser {

    private static final Logger logger = MyLogger.getLogger();


    public static void RegisterUser() {


        try {

            PojoCreateUpdateUserRequest rq = UserGenerator.createUserWithMaxCharacters();

            PojoCreateUserResponse rs = given()
                    .basePath(createUser)
                    .body(rq)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoCreateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoCreateUserResponse::getName, PojoCreateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());

            logger.info("The user with max characters body has been created");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}
