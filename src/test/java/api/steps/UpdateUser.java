package api.steps;

import api.pojo.PojoCreateUpdateUserRequest;
import api.pojo.PojoCreateUserResponse;
import api.pojo.PojoUpdateUserResponse;
import api.specification.Specification;
import logger.MyLogger;
import org.slf4j.Logger;
import utils.UserGenerator;

import static api.endpoints.Endpoints.updateUser;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateUser {

    private static final Logger logger = MyLogger.getLogger();

    public static void updateExistingUser() {
        try {
            PojoCreateUpdateUserRequest rq = UserGenerator.updateSimpleUser();

            PojoUpdateUserResponse rs = given()
                    .spec(Specification.requestSpec())
                    .basePath(updateUser)
                    .body(rq)
                    .log().body()
                    .when().put()
                    .then()
                    .spec(Specification.responseSpecOK200())
                    .extract().as(PojoUpdateUserResponse.class);

            assertThat(rs)
                    .isNotNull()
                    .extracting(PojoUpdateUserResponse ::getName, PojoUpdateUserResponse::getJob)
                    .containsExactly(rq.getName(), rq.getJob());
            logger.info(" The user has been updated ");

        } catch (Exception e) {
            logger.error(" the error has happened when user was updated " + e.getMessage());
        }

    }



}
