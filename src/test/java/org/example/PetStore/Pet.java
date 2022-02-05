package org.example.PetStore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Petstore.MyDTO;
import org.example.Petstore.SuccessPet;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Pet extends AbstractTest {
    public static Long idP;
    @Test
    void addPet() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();

        MyDTO myDTO = new MyDTO();
        myDTO.setId(0);

        MyDTO.Category category =new MyDTO.Category();
        category.setId(0);
        category.setName((String) prop.get("petName"));
        myDTO.setCategory(category);
        myDTO.setName("doggie");
        myDTO.setPhotoUrls(new String[] {"string"});
        MyDTO.Tags  inTags =new MyDTO.Tags();
        inTags.setId(0);
        inTags.setName("string");
        myDTO.setTags(new Object[] {inTags});
        myDTO.setStatus("available");
        String result=objectMapper.writeValueAsString(myDTO);

        SuccessPet successAddPet = given()
                .body(result)
                 .accept("application/json")
                .contentType("application/json")
                .when()
                .post(url+"pet")
                .then()
                //.body("category.name", response -> equalTo((String) prop.get("petName")))
                .extract()
                .as(SuccessPet.class);
                 assertThat(successAddPet.getCategory().getName(), equalTo((String) prop.get("petName")));
        idP =successAddPet.getId();


                    }


    @Test
    void updateAnExistingPet() {
        MyDTO myDTO = new MyDTO();
        myDTO.setId(idP);

        MyDTO.Category category =new MyDTO.Category();
        category.setId(0);
        category.setName("string");
        myDTO.setCategory(category);
        myDTO.setName("doggie");
        myDTO.setPhotoUrls(new String[] {"string"});
        MyDTO.Tags  inTags =new MyDTO.Tags();
        inTags.setId(0);
        inTags.setName("string");
        myDTO.setTags(new Object[] {inTags});
        myDTO.setStatus("available");
         SuccessPet successPutPet = given()
                .body(myDTO)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .put(url+"pet")
                .then()
                 .extract()
                 .as(SuccessPet.class);
                 assertThat(successPutPet.getCategory().getName(), equalTo("string"));

    }
    @Test
    void deletePet() {
       SuccessPet successDeletePet = given()
                .accept("application/json")
                //.header("api_key",(String) prop.get("petName"))
                .when()
               .contentType("application/json")
                .delete(url+"pet/{idP}",idP)
                .then()
               .extract()
               .as(SuccessPet.class);
               assertThat(successDeletePet.getCode(),equalTo(200));



    }

    @Test
    void findByStatus() {
        List <SuccessPet> successPetList = given()
                .queryParams("status", "available")
                .accept("application/json")
                .when()
                .get(url + "pet/findByStatus")
                .then().log().all()
                .extract()
                .body()
                .jsonPath()
                .getList(".",SuccessPet.class);
        System.out.println(successPetList);
        successPetList.stream().forEach(x->assertThat(x.getStatus() ,equalTo("available")));




    }
    @Test
    void uploadImage() {
        given()
                .multiPart(new File("src/test/resources/catPicture.jpg"))
                .contentType("multipart/form-data")
                .accept("application/json")
                .when()
                .post(url+"pet/"+idP+"/uploadImage")
                .prettyPeek();


    }
}
