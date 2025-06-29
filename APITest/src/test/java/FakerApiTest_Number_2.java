import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FakerApiTest_Number_2 {

    @DataProvider(name = "quantityProvider")
    public Object[][] quantityProvider() {
        return new Object[][] {
                {200},
                {5},
                {1}
        };
    }

    @Test(dataProvider = "quantityProvider")
    public void testCompanyApiWithDifferentQuantities(int quantity) {
        String baseUrl = "https://fakerapi.it/api/v1/companies";

        Response response = RestAssured
                .given()
                .queryParam("_quantity", quantity)
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        int actualSize = response.jsonPath().getList("data").size();

        System.out.println("Requested amount: " + quantity + ", Returned: " + actualSize);
        assertEquals(actualSize, quantity, "doesn't match requested");
    }
}
