import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class petsTestJsonPath {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test // get pet with Id -286,
    public void validateStatusCode() {
        get("/pet/-286").then().statusCode(200);
    }

    @Test
    public void validateTest() {
        Response response = get("/pet/112238");
        List<String> jsonResponse = response.jsonPath().getList("photoUrls");
        //get the count of photo urls for this pet
        System.out.println("Photo urls size - " +jsonResponse.size());

        //get the name of the pet
        String petName = response.jsonPath().getString("name");
        System.out.println("Pet name - " +petName);

        //get the status of the pet
        String petStatus = response.jsonPath().getString("status");
        System.out.println("Pet status - " +petStatus);

        //get the category name of the pet
        String catName = response.jsonPath().getString("category.name");
        System.out.println("Category name - " +catName);

        List<Map<String, Integer>> listTags = response.jsonPath().getList("tags");
        System.out.println(listTags.get(1));

        //Get first item from the list
        String firstItem = response.jsonPath().getString("tags.name[0]");
        System.out.println("First tag name - " +firstItem);

        Map<String, Integer> mapTags = response.jsonPath().getMap("tags[0]");
        System.out.println(mapTags);

    }

}
