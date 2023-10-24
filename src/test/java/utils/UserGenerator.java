package utils;

import api.pojo.PojoCreateUpdateUserRequest;
import api.pojo.PojoRegisterUserRequest;
import api.pojo.PojoRegisterUserResponse;

public class UserGenerator {


    public static PojoCreateUpdateUserRequest createSimpleUser() {
        return PojoCreateUpdateUserRequest.builder()
                .name(" Nick ")
                .job(" Software Engineer in Test ")
                .build();

    }

    public static PojoCreateUpdateUserRequest updateSimpleUser() {
        return PojoCreateUpdateUserRequest.builder()
                .name(" Nickolay ")
                .job(" Software Engineer ")
                .build();

    }

    public static PojoCreateUpdateUserRequest createUserWithMinCharacters() {
        return PojoCreateUpdateUserRequest.builder()
                .name("n")
                .job("P")
                .build();

    }

    public static PojoCreateUpdateUserRequest createUserWithMaxCharacters() {
        return PojoCreateUpdateUserRequest.builder()
                .name("nsfsfsfsfsfsdfkkddkkdkdkddkdkdkdkdkdkdkdkdkdkkdkdkdkdkdkddkdkdkdkkdkdkdkdkdkdsdfsfSSSSdfFSFSFSFSFFS")
                .job("PDADASDDADADADASDASDKKNNNBM<N<N<N<<NDADADADADADDDDASDADASDADADADADASDADADAASDDAdfsdfsfsfsfsfsfsfsFDFFSSFSF")
                .build();

    }

    public static PojoCreateUpdateUserRequest createUserWithEmptyBrackets() {
        return PojoCreateUpdateUserRequest.builder()
                .name("")
                .job("")
                .build();

    }

    public static PojoRegisterUserResponse responseNewUser(){
        return PojoRegisterUserResponse.builder()
                .id(4)
                .token("QpwL5tke4Pnpja7X4")
                .build();
    }

    public static PojoRegisterUserRequest regNewUser(){
        return PojoRegisterUserRequest.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
    }





}
