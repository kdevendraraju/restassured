import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;

public class petsTestResponseBody {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test // get pet with Id -286,
    public void validateStatusCode() {
        get("/pet/-286").then().statusCode(200);
    }

    @Test // get pet with Id -286 and validate category
    public void validateStatusCodeAndResponseContent() {
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("category.id", equalTo(-847));
    }

    /*The assertThat method is a stylized sentence for making a test assertion.
    In the above example, We are trying to verify if the first method parameter 'category.id' is equal to -847. */

    @Test // get pet with Id -286 and validate tags has Id
    public void validateStatusCodeAndMultipleTagIds() {
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("tags", hasItems(-309, -932, 290));
    }

    @Test // get pet with Id -286 and validate photo urls has 9 array elements
    public void validateStatusCodeAndPhotoURLsSize() {
        //verifies that the size of a list is correct,
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("photoUrls", hasSize(6));
    }

    @Test
    public void multipleMatchersExampleAnyOf() {
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("status", anyOf(
                        (equalToIgnoringCase("Pending")),
                        (equalToIgnoringCase("xys"))));
    }

    @Test
    public void multipleMatchersExampleAnyOfWithList() {
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("photoUrls", anyOf(
                        (hasItem("gF2fSj4l5ui3V0uD")),
                        (hasItem("iTWGHgc3AXKNuxTO"))));
    }

    @Test
    public void multipleMatchersExampleAllOf() {
        get("/pet/-286").then().statusCode(200).assertThat()
                .body("status", allOf(
                        (equalToIgnoringCase("Pending")),
                        (startsWith("pen")),
                        (containsString("tdin"))));
    }

   // Below are some of the useful matchers

    //equalTo
    //hasItem, hasItems - To verify that list contains the given element
    //hasItemInArray - test an array contains an element
    //greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo - test ordering
    //equalToIgnoringCase - test string equality ignoring case
    //containsString, endsWith, startsWith - test string matching
    //is(false/tru) - If we want to verify that a boolean value is false/true
    //containsInAnyOrder -  To verify that the list contains only the expected elements in any order
   // Check for more details http://hamcrest.org/JavaHamcrest/javadoc/2.1/



}
