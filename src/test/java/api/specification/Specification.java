package api.specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.endpoints.Endpoints.*;

public class Specification {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification incorrectUrlRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(incorrectUrl)
                .setContentType(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecBadRequest400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification response201Created() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification response404NotFound() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.HTML)
                .expectStatusCode(404)
                .build();
    }

 public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
     RestAssured.requestSpecification = request;
     RestAssured.responseSpecification = response;
 }

}
