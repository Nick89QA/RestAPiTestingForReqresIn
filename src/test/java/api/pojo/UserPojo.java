package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
public class UserPojo {
    private Integer id;
    private String email;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    private String avatar;

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private ArrayList<UserPojo> data;
    private Support support;

    public class Support {
        private String url;
        private String text;
    }
}


