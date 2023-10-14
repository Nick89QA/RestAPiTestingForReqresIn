package utils;

import api.pojo.CreateUserRequest;

public class UserGenerator {


    public static CreateUserRequest createSimpleUser() {
        return CreateUserRequest.builder()
                .name(" Nick ")
                .job(" Software Engineer in Test ")
                .build();

    }

    public static CreateUserRequest createUserWithDiffParams() {
        return CreateUserRequest.builder()
                .name("n")
                .job("P")
                .build();

    }
}
