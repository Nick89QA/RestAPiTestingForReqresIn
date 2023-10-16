package api.steps;

import api.pojo.PojoListResource;

import java.util.List;

import static api.endpoints.Endpoints.getListResource;
import static io.restassured.RestAssured.given;

public class ListResource {

    public static void getListResource() {
        List<PojoListResource> list = given()
                .basePath(getListResource)
                .when().get()
                .then().log().all()
                .extract().body().jsonPath().getList("data", PojoListResource.class);
    }
}
