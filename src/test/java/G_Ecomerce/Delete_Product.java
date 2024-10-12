package G_Ecomerce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Delete_Product {
public static void main(String[] args) throws IOException {
	
	String tocken="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NjBkNjM5OGE4NmY4Zjc0ZGNiNjJlMmQiLCJ1c2VyRW1haWwiOiJ2aW5vdGhrZ3MxOC4wMUBnbWFpbC5jb20iLCJ1c2VyTW9iaWxlIjo5NTk3MzU5MDM3LCJ1c2VyUm9sZSI6ImN1c3RvbWVyIiwiaWF0IjoxNzI4MTExMzgwLCJleHAiOjE3NTk2Njg5ODB9.TEJDc2xjgXJuKzvArnVeb8KfAFxdc8H_qwlu_hsC_5M\r\n"
			+ "";
	
	RestAssured restAssured= new RestAssured();
	restAssured.baseURI="https://rahulshettyacademy.com/";
	
	RequestSpecification requestSpecification = restAssured.given().header("Content-Type","application/json").header("Authorization",tocken).pathParam("id", "6700f86aae2afd4c0b8fd0ab");

	
	Response response = requestSpecification.when().delete("api/ecom/product/delete-product/{id}");
	
	String value = response.then().extract().asPrettyString();
	
	System.out.println(value);
}
}
