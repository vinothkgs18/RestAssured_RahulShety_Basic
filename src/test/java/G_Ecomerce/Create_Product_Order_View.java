package G_Ecomerce;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Create_Product_Order_View extends Login_GetTocken{
	
	public static void main(String[] args){
		
		RestAssured restAssured= new RestAssured();
		restAssured.baseURI="https://rahulshettyacademy.com/";
		String tocken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NjBkNjM5OGE4NmY4Zjc0ZGNiNjJlMmQiLCJ1c2VyRW1haWwiOiJ2aW5vdGhrZ3MxOC4wMUBnbWFpbC5jb20iLCJ1c2VyTW9iaWxlIjo5NTk3MzU5MDM3LCJ1c2VyUm9sZSI6ImN1c3RvbWVyIiwiaWF0IjoxNzI4MTExMzgwLCJleHAiOjE3NTk2Njg5ODB9.TEJDc2xjgXJuKzvArnVeb8KfAFxdc8H_qwlu_hsC_5M\r\n"
				+ "";
		
		RequestSpecification requestSpecification = restAssured.given().header("Authorization",tocken)
				.param("productName", "HP Laptop")
				.param("productAddedBy","660d6398a86f8f74dcb62e2d")
				.param("productCategory", "fashion")
				.param("productSubCategory", "shirts")
				.param("productPrice", "2500")
				.param("productDescription", "Smart Watch Noise")
				.param("productFor", "women")
				.multiPart("productImage",new File("C://Users//vinot//Downloads//TV.jpg"));
		Response response = requestSpecification.when().post("api/ecom/product/add-product");
		
		String value = response.then().extract().asPrettyString();
		int statusCode = response.then().extract().statusCode();
		System.out.println(statusCode);
		
		System.out.println(value);
		System.out.println("----------------------------------------------------------------------------------------------");
		
		RequestSpecification requestSpecification1 = null;
		try {
			requestSpecification1 = restAssured.given().header("Content-Type","application/json").header("Authorization",tocken).body(new String(Files.readAllBytes(Paths.get("src/test/java/G_Ecomerce/createOrder.json"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response response1 = requestSpecification1.when().post("api/ecom/order/create-order");
		String result = response1.then().extract().asPrettyString();
		System.out.println(response1.statusCode());
		System.out.println(result);
		System.out.println("-----------------------------------------------------------------------------------------------");
		
		RequestSpecification requestSpecification2 = restAssured.given().header("Content-Type","application/json").header("Authorization",tocken)
		.queryParam("id", "6700b828ae2afd4c0b8f976e");
		
		Response response2 = requestSpecification2.when().get("api/ecom/order/get-orders-details");
		
		String value2 = response2.then().extract().asPrettyString();
		
		System.out.println(value2);
	}
	
	

}
