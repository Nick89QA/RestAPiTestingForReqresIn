package api.tests;

import api.pojo.PojoListOfUsers;
import api.specification.Specification;
import api.steps.ListResource;
import api.steps.NewUser;
import api.steps.SingleUser;
import logger.MyLogger;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;

public class AllTheUsersTest {


    private static final Logger logger = MyLogger.getLogger();

    /**
     * ---List of users---
     */

    @Test //positive
    public void getListOfUsersAndMatchEmailAndFirstName() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        api.steps.ListOfUsers.getUsers();

    }

    @Test //positive
    public void getListOfUsers() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        List<PojoListOfUsers> users = api.steps.ListOfUsers.getAllUsers();

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        logger.info("--We match all avatars with id--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
        logger.info("--We match all emails which ends on @reqres.in--: PASSED");

        Assertions.assertTrue(users.stream().allMatch(x -> x.getAvatar().endsWith("jpg")));
        logger.info("--We match all avatars and format is jpg--: PASSED");
    }

    @Test //positive
    public void matchNumberOfArray() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        api.steps.ListOfUsers.checkAllNumberInArray();

    }

    @Test //positive
    public void checkFieldAvatarInTheArray() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        api.steps.ListOfUsers.checkTheFieldAvatar();

    }

    /**
     * ---New User---
     */
    @Test
    public void createANewUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        NewUser.createUser();
    }

    @Test
    public void createUserWithIncorrectPath() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response404NotFound());
        NewUser.createUserWrongPath();

    }

    @Test
    public void createUserWithInCorrectUrl() {
        Specification.installSpecification(Specification.requestSpecIncorrectUrl(), Specification.response404NotFound());
        NewUser.createUserWithWrongUrl();
    }

    @Test
    public void createUserWithLargeBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        NewUser.createUserWithMaximumCharBody();

    }

    @Test
    public void createUserWithMinCharBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        NewUser.createUserWithMinimumCharBody();

    }

    @Test
    public void createUserWithEmptyBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        NewUser.createUserWithEmptyBody();

    }

    /**
     * ---Single user---
     */

    @Test
    public void getUserAndMatchDataInResponse() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        SingleUser.getSingleUser();

    }

    @Test
    public void getUserWithIncorrectUrl() {
        Specification.installSpecification(Specification.requestSpecIncorrectUrl(), Specification.response404NotFound());
        SingleUser.checksStatusCodeWIthIncorrectUrl();

    }

    @Test
    public void getUserWithIncorrectPath() {
        Specification.installSpecification(Specification.requestSpecIncorrectUrl(), Specification.response404NotFound());
        SingleUser.checksStatusCodeWIthIncorrectPath();

    }

    @Test
    public void getListResourceAndSorted() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListResource.listResourceSortByYears();

    }
    @Test
    public void checkFieldsNotNull() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListResource.checkFieldsInResponseNotNull();


    }
    @Test
    public void checkFieldsId() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListResource.checkThatIdIsUnique();
    }

    @Test
    public void checkFieldPantoneValue() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListResource.checkFieldPantoneValue();
    }
    @Test
    public void checkFieldColor() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListResource.checkFieldColor();
    }


}