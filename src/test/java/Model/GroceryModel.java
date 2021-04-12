package Model;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GroceryModel {

    public String baseURL="http://localhost:3000/allGrocery";
    JSONObject request = new JSONObject();


    public void checkProductNamePriceAndStock(String productName,int price,int unitsInStock){

        RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("name",productName)
                .when()
                .get(baseURL)
                .then()
                .statusCode(200)
                .body("price[0]", equalTo(price))
                .body("stock[0]", equalTo(unitsInStock));
    }


    public void checkAllProductFromList(){
        Response response = given()
                .get(baseURL)
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertNotNull(response.getBody().jsonPath().getList("id").get(0));
    }


    public void postProductWithIdNamePriceAndStock(int id,String productName,int price,int unitsInStock){

        request.put("id",id );
        request.put("name", productName);
        request.put("price", price);
        request.put("stock", unitsInStock);

        given()
                .header("Content-type", "application/json")
                .and()
                .body(request.toJSONString())
                .when()
                .post("http://localhost:3000/allGrocery")
                .then()
                .statusCode(201);

    }
}
