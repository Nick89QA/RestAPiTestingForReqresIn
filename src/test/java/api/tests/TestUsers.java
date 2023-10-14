package api.tests;

import api.pojo.UsersListPojo;
import api.specification.Specification;
import api.steps.ListOfUsers;
import logger.MyLogger;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.List;

public class TestUsers {


    private static final Logger logger = MyLogger.getLogger();

    @Test //positive
    public void getListOfUsersAndMatchEmailAndFirstName() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListOfUsers.getUsers();

    }

    @Test //positive
    public void getListOfUsers() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        List<UsersListPojo> users = ListOfUsers.getAllUsers();

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
        ListOfUsers.checkAllNumberInArray();

    }

    @Test //positive
    public void checkFieldAvatarInTheArray() {
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());
        ListOfUsers.checkTheFieldAvatar();

    }

}