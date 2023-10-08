package api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import utils.DateDeserializer;

import java.time.OffsetDateTime;

@Data
public class CreateUserResponse {
 private String name;
 private String job;
 private int id;
 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
 @JsonDeserialize(using = DateDeserializer.class)
 private OffsetDateTime createdAt;


}
