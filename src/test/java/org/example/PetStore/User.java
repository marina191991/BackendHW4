package org.example.PetStore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.Petstore.MyDTO;
import org.example.Petstore.UserDTO;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class User extends AbstractTest {

    @Test
    void CreateWithArray()  {
        MyDTO myDTO =new MyDTO();
        myDTO.setId(0);
        myDTO.setUsername("string");
        myDTO.setFirstName("string");
        myDTO.setLastName("string");
        myDTO.setEmail("string");
        myDTO.setPassword("string");
        myDTO.setPhone("string");
        myDTO.setUserStatus(0);
        List <MyDTO> users = new ArrayList<MyDTO>();
        users.add(myDTO);
              given()
                .body(users)
                .contentType("application/json")
                .when()
                .post(url + "user/createWithArray")
                .then()
                .body("type", response -> equalTo("unknown"))
                .assertThat().header("access-control-allow-headers", "Content-Type, api_key, Authorization");


    }

    @Test
    void CreateWithList()  {
        MyDTO myDTO =new MyDTO();
        myDTO.setId(0);
        myDTO.setUsername("string");
        myDTO.setFirstName("string");
        myDTO.setLastName("string");
        myDTO.setEmail("string");
        myDTO.setPassword("string");
        myDTO.setPhone("string");
        myDTO.setUserStatus(0);
        List <MyDTO> users = new ArrayList<MyDTO>();
        users.add(myDTO);
             given()
                .body(users)
                .log().all()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .post(url + "user/createWithList")
                .then()
                .body("type", response -> equalTo("unknown"))
                .body("message", response -> equalTo("ok"));
    }

    @Test
    void GetUserByUserName() {
               UserDTO user = given()
                .when()
                .get(url + "user/" + (String) prop.get("user"))
                .then()
                .log().all()
                .extract()
                .body()
                .as(UserDTO.class);
        assertThat(user.getUsername(), equalTo((String) prop.get("user")));

    }


    @Test
    void loginUserIntoSystem() {
       given()
                .queryParam("username", (String) prop.get("userName"))
                .queryParam("password", (String) prop.get("password"))
                .accept("application/json")
                .when()
                .get(url + "user/login");

    }


    @Test
    void deleteUser() {
        UserDTO user =  given()
                .accept("application/json")
                .when()
                .delete(url+"user/"+(String) prop.get("user"))
                .then()
                 .extract()
                .body()
                .as(UserDTO.class);
        assertThat(user.getMessage(), equalTo((String) prop.get("user")));

    }
}