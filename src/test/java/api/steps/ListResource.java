package api.steps;

import api.pojo.PojoListResource;
import logger.MyLogger;
import org.junit.Assert;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static api.endpoints.Endpoints.getListResource;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class ListResource {

    private static final Logger logger = MyLogger.getLogger();


    public static void listResourceSortByYears() {
        List<PojoListResource> listColors = given()
                .basePath(getListResource)
                .when().get()
                .then().log().all()
                .extract().body().jsonPath().getList("data", PojoListResource.class);
        List<Integer> years = listColors.stream().map(PojoListResource::getYear).collect(Collectors.toList());
        logger.info("we extract years from response--Passed");
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        logger.info("we sort years from response--Passed");

        List<String> name = listColors.stream().map(PojoListResource::getName).collect(Collectors.toList());
        logger.info(name + " we extract names from response--Passed");
        Assert.assertEquals(sortedYears, years);
        logger.info("we match years and sorted years from response--Passed");

    }

    public static void checkThatFieldsInResponseNotNull() {
       try {

           List<PojoListResource> resources = given()
                   .basePath(getListResource)
                   .log().body()
                   .when().get()
                   .then().log().all().extract().jsonPath().getList("data", PojoListResource.class);

           for (PojoListResource resource : resources) {
               assertThat(resource.getId()).isNotNull();
               assertThat(resource.getName()).isNotNull();
               assertThat(resource.getYear()).isNotNull();
               assertThat(resource.getColor()).isNotNull();
               assertThat(resource.getPantone_value()).isNotNull();
               logger.info("we checked all the fields not Null--Passed");

           }
       }catch (Exception e) {
           logger.error("the error has happened when whe checked fields on Null" + e.getMessage());
       }

    }

}

