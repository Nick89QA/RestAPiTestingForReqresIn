package api.tests;

import api.pojo.PojoListOfUsers;
import api.specification.Specification;
import api.steps.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import logger.MyLogger;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;


public class AllTheUsersTest {


    private static final Logger logger = MyLogger.getLogger();


    /**
     * ---New User---
     */
    @Test
    public void CreateANewUser() {
        CreateNewUser.createUser();
    }

    @Test
    public void UpdateExistUser() {
        UpdateUser.updateExistingUser();

    }


    @Test
    public void createUserWithIncorrectPath() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response404NotFound());
        CreateNewUser.createUserWrongPath();

    }

    @Test
    public void createUserWithInCorrectUrl() {
        Specification.installSpecification(Specification.requestSpecIncorrectUrl(), Specification.response404NotFound());
        CreateNewUser.createUserWithWrongUrl();
    }

    @Test
    public void createUserWithLargeBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        CreateNewUser.createUserWithMaximumCharBody();

    }

    @Test
    public void createUserWithMinCharBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        CreateNewUser.createUserWithMinimumCharBody();

    }

    @Test
    public void createUserWithEmptyBody() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        CreateNewUser.createUserWithEmptyBody();

    }

    @Test
    public void createUserWithEmptyBrack() {
        Specification.installSpecification(Specification.requestSpec(), Specification.response201Created());
        CreateNewUser.createUserWithEmptyBracket();

    }


    /**
     * ---List of users---
     */

    @And("^We are getting a list of users using method GET$")
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

    @Test
    public void SucRegUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        SuccessRegisterUser.RegisterUser();

    }

    @Test
    public void UnsRegUser() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecBadRequest400());
        UnsuccessfulRegisterUser.UnsRegisterUser();

    }

}