package G_Ecomerce;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login_GetTocken {
	static String token;
	static String userId;
	
	public static void main(String[] args) {
		
		
		
		RestAssured restAssured= new RestAssured();
		restAssured.baseURI="https://rahulshettyacademy.com/";
		
		RequestSpecification requestSpecification = restAssured.given().header("Content-Type","application/json").body("{\"userEmail\":\"vinothkgs18.01@gmail.com\",\"userPassword\":\"Spring*123\"}");
		
		Response response = requestSpecification.when().post("api/ecom/auth/login");
		
		String value = response.then().extract().asPrettyString();
		System.out.println(value);
		
		JsonPath jp= new JsonPath(value);
		token= jp.getString("token");
		userId=jp.getString("userId");
		
		System.out.println(token);
		
	}

}
