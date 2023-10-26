package api.steps;

import api.pojo.*;
import logger.MyLogger;
import org.junit.Assert;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.registerNewUser;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SuccessRegisterUser {

    private static final Logger logger = MyLogger.getLogger();


    public static void RegisterUser() {

        try {

            PojoRegisterUserRequest requestUser = UserGenerator.registerUser();
            PojoRegisterUserResponse responseUser = UserGenerator.responseRegisterUser();

            PojoRegisterUserResponse response = given()
                    .basePath(registerNewUser)
                    .body(requestUser)
                    .log().all()
                    .when().post()
                    .then().log().body().extract().as(PojoRegisterUserResponse.class);
            Assert.assertEquals(response.getToken(), responseUser.getToken());
            logger.info("We assert token from response, with token from userGenerator response");

            Assert.assertEquals(response.getId(), responseUser.getId());
            logger.info("We assert id from response, with id from userGenerator response");


            logger.info("The user has been registered");
        } catch (Exception e) {
            logger.error(" the error has happened when user was created " + e.getMessage());
        }

    }
}
