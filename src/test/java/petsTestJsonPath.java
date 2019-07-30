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
        Response response = get("/pet/-286");
        List<String> jsonResponse = response.jsonPath().getList("photoUrls");
        //get the count of photo urls for this pet
        System.out.println(jsonResponse.size());

        //get the name of the pet
        String petName = response.jsonPath().getString("name");
        System.out.println(petName);

        //get the status of the pet
        String petStatus = response.jsonPath().getString("status");
        System.out.println(petStatus);

        List<Map<String, Integer>> allTags = response.jsonPath().getList("tags");
        System.out.println(allTags.get(1));

    }

    @Test
    public void testSchema() {
        get("/pet/1076").then().statusCode(200).assertThat().body(matchesJsonSchemaInClasspath("matchJ.json"));

    }
}
