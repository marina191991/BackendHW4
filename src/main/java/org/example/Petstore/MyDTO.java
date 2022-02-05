package org.example.Petstore;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import sun.security.krb5.internal.ccache.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyDTO {

        private long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private Integer userStatus;
        private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private Boolean complete;
    private Category category;
    private String name;
    private Object[] tags;
    private Tags inTags;
    private String[] photoUrls;
@Data
public static class Tags {
    private Integer id;
    private String name;
}


    @Data
    public static class Category {

        private Integer id;
        private String name;
    }

}

