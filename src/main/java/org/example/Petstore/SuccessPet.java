package org.example.Petstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public  class SuccessPet {
    private Long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
    private Integer code;
    private String type;
    private String message;



    @Data
        public static class Category {
        private  Integer id;
        private String name;


    }
    @Data
    public static class Tag{
        private Integer id;
        private String name;
    }




}
