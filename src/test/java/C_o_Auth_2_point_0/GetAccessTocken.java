package C_o_Auth_2_point_0;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
public class GetAccessTocken {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		RequestSpecification requestSpecification = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust");
		
		Response response = requestSpecification.when().post("/oauthapi/oauth2/resourceOwner/token");
		
		
		String token = response.then().extract().asPrettyString();
		int statusCode = response.then().extract().statusCode();
		
		System.out.println(token);
		
		System.out.println(statusCode);
		
	}

}
