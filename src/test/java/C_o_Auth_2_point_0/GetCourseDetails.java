package C_o_Auth_2_point_0;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class GetCourseDetails {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		RequestSpecification requestSpecification = given().queryParam("access_token", "8OOnf4b+VHPnyotRXQvW5g==");
		Response response = requestSpecification.when().get("/oauthapi/getCourseDetails");
		String string = response.then().extract().asPrettyString();
		System.out.println(string);
		
	}

}
