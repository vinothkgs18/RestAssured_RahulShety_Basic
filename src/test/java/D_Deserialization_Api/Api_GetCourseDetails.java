package D_Deserialization_Api;

import static io.restassured.RestAssured.given;

import D_DeSerialization_Pojo.GetCourseDetails;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Api_GetCourseDetails {
	public static void main(String[] args) {
		String tocken = null;
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		System.out.println("---------------------Get Tocken------------------------------");
		RequestSpecification requestSpecification = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
				.formParams("scope", "trust");

		Response response = requestSpecification.when().post("/oauthapi/oauth2/resourceOwner/token");

		String body = response.then().extract().asPrettyString();
		JsonPath jp= new JsonPath(body);
		tocken=jp.getString("access_token");
		System.out.println("Tocken= "+tocken);
		

		System.out.println("---------------------GetCourse Details------------------------------");
		RequestSpecification requestSpecification1 = given().queryParam("access_token", tocken);
		Response response2 = requestSpecification1.when().get("/oauthapi/getCourseDetails");
		GetCourseDetails as = response2.as(GetCourseDetails.class);
		System.out.println(as.getInstructor());
		System.out.println(as.getCourses().getWebAutomation().get(2).getCourseTitle());

		
		

	}

}
