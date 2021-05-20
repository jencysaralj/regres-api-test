import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class testUsers {
    private static String postBody = "{\n" +
            "  \"name\": \"morpheus\",\n" +
            "  \"job\": \"Leader\"\n}";
    @Test
    public void testGetUsers(){
         given().
                 get("https://reqres.in/api/users?page=2")
                 .then()
                 .statusCode(200);
    }
    @Test
    public void testPostUsers(){

      Response response = given()
                .header("Content-type", "application/json")
                .body(postBody)
                .and()
                .post("https://reqres.in/api/users")
                .then()
                .extract().response();
        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("Leader", response.jsonPath().getString("job"));


    }
}
