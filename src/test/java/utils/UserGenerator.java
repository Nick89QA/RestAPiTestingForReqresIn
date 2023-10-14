package utils;

import api.pojo.CreateUserRequest;

public class UserGenerator {


    public static CreateUserRequest getSimpleUser() {
        return CreateUserRequest.builder()
                .name(" Nick ")
                .job(" Software Engineer in Test ")
                .build();

    }

}
