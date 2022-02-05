package org.example.PetStore;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.Petstore.MyDTO;
import org.example.Petstore.UserDTO;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class Store extends AbstractTest {



    @Test
     void OrderPlaced() {
        MyDTO myDTO = new MyDTO();
               myDTO.setId(0);
                myDTO.setPetId(0);
                myDTO.setQuantity(0);
                myDTO.setStatus("placed");
                myDTO.setComplete(true);
        given().body(myDTO)
                .contentType("application/json")
                .when()
                .post(url+"store/order")
                ;



}
    @Test
    void FindOrderById() {
        UserDTO order = given()
                    .when()
                    .get(url+"store/order/"+((String) prop.get("orderId")))
                    .then()
                .log().all()
                    .extract()
                .body()
                .as(UserDTO.class);
                assertThat(String.valueOf(order.getId()),equalTo((String) prop.get("orderId")));

    }
    @Test
    void Inventory() {
        given()
        .when()
                .get(url+"store/inventory")
                .then().log().all();

    }



}
