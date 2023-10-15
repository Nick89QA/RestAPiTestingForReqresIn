package utils;

import api.pojo.CreateUserRequest;

public class UserGenerator {


    public static CreateUserRequest createSimpleUser() {
        return CreateUserRequest.builder()
                .name(" Nick ")
                .job(" Software Engineer in Test ")
                .build();

    }

    public static CreateUserRequest createUserWithMinCharacters() {
        return CreateUserRequest.builder()
                .name("n")
                .job("P")
                .build();

    }

    public static CreateUserRequest createUserWithMaxCharacters() {
        return CreateUserRequest.builder()
                .name("nsfsfsfsfsfsdfkkddkkdkdkddkdkdkdkdkdkdkdkdkdkkdkdkdkdkdkddkdkdkdkkdkdkdkdkdkdsdfsfSSSSdfFSFSFSFSFFS")
                .job("PDADASDDADADADASDASDKKNNNBM<N<N<N<<NDADADADADADDDDASDADASDADADADADASDADADAASDDAdfsdfsfsfsfsfsfsfsFDFFSSFSF")
                .build();

    }

    public static CreateUserRequest createUserWithEmptyBrackets() {
        return CreateUserRequest.builder()
                .name("")
                .job("")
                .build();

    }

}
