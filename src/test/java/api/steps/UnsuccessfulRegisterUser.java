package api.steps;

import logger.MyLogger;
import org.slf4j.Logger;

import static api.endpoints.Endpoints.registerNewUser;
import static io.restassured.RestAssured.given;

public class UnsuccessfulRegisterUser {
    private static final Logger logger = MyLogger.getLogger();


    public static void UnsRegisterUser() {

                      given()
                    .basePath(registerNewUser)
                    .body("sydney@fife")
                    .log().all()
                    .when().post()
                    .then().log().all();


    }

}
