package utils;

import api.pojo.PojoSingleUserRequest;

public class UserGenerator {


    public static PojoSingleUserRequest createSimpleUser() {
        return PojoSingleUserRequest.builder()
                .name(" Nick ")
                .job(" Software Engineer in Test ")
                .build();

    }

    public static PojoSingleUserRequest createUserWithMinCharacters() {
        return PojoSingleUserRequest.builder()
                .name("n")
                .job("P")
                .build();

    }

    public static PojoSingleUserRequest createUserWithMaxCharacters() {
        return PojoSingleUserRequest.builder()
                .name("nsfsfsfsfsfsdfkkddkkdkdkddkdkdkdkdkdkdkdkdkdkkdkdkdkdkdkddkdkdkdkkdkdkdkdkdkdsdfsfSSSSdfFSFSFSFSFFS")
                .job("PDADASDDADADADASDASDKKNNNBM<N<N<N<<NDADADADADADDDDASDADASDADADADADASDADADAASDDAdfsdfsfsfsfsfsfsfsFDFFSSFSF")
                .build();

    }

    public static PojoSingleUserRequest createUserWithEmptyBrackets() {
        return PojoSingleUserRequest.builder()
                .name("")
                .job("")
                .build();

    }

}
