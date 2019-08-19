import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class petsUserTest {

    String jsonObjectPet = "{" +
            "  \"id\": 112238," +
            "\"category\": {" +
            "      \"id\": 1," +
            "      \"name\": \"updated cat\"" +
            "    }," +
            "  \"name\": \"doggie updated\"," +
            "  \"photoUrls\": [" +
            "    \"string\"" +
            "  ]," +
            "  \"tags\": [" +
            "    {" +
            "      \"id\": 0," +
            "      \"name\": \"string\"" +
            "    }" +
            "  ]," +
            "  \"status\": \"available\"" +
            "}";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testSchema() {

        Response response = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(jsonObjectPet)
                .when()
                .put("/pet");

        System.out.println(response.getBody().toString());
        String id = response.jsonPath().get("id").toString();

        get("/pet/"+id+"").
                then().statusCode(200).assertThat().body(matchesJsonSchemaInClasspath("petUpdate.json"));

    }
}
