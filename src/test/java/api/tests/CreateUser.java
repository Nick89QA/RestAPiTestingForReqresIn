package api.tests;

import api.specification.Specification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {



    @Test
    public void createUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());

        given()


    }
}
