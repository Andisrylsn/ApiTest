import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

public class FakerApiTest_Number_3 {

    @Test
    public void validateCompanyIdAndSchema() {
        Response response = RestAssured
                .given()
                .queryParam("_quantity", 10)
                .when()
                .get("https://fakerapi.it/api/v1/companies");

        response.then().statusCode(200);


        List<Map<String, Object>> companies = response.jsonPath().getList("data");

        for (Map<String, Object> company : companies) {
            assertNotNull(company.get("id"), "ID should not be null");
        }


        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("faker_api_schema.json"));
    }
}

