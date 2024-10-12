package F_ReqSpecBuilder_ResponseSpecBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddPlaceApi {
	public static void main(String[] args) throws IOException {
		RequestSpecBuilder rsb=new RequestSpecBuilder();
		RequestSpecification rs = rsb.setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		RestAssured restAssured= new RestAssured();
		
		//Add place api
		System.out.println("-------------------------------------POST add place------------------------------------------------");
		
		RequestSpecification rs1 = restAssured.given().spec(rs).body(new String(Files.readAllBytes(Paths.get("src/test/java/F_ReqSpecBuilder_ResponseSpecBuilder/payLoad.json"))));
		Response response = rs1.when().post("/maps/api/place/add/json");
		String value = response.then().extract().asPrettyString();
		JsonPath jp= new JsonPath(value);
		String place_id = jp.getString("place_id");
		System.out.println(value);
		
		
		//GET place Api
		System.out.println("-------------------------------------GET place------------------------------------------------");
		RequestSpecification spec = restAssured.given().spec(rs).queryParam("place_id", place_id);
		Response response2 = spec.when().get("/maps/api/place/get/json");
		String value1 = response2.then().extract().asPrettyString();
		System.out.println(value1);
		
		//PUT place API
		System.out.println("-------------------------------------PUT Update place------------------------------------------------");
		
		RequestSpecification requestSpec = restAssured.given().spec(rs).body(new String(Files.readAllBytes(Paths.get("src/test/java/F_ReqSpecBuilder_ResponseSpecBuilder/PUTPayload.json"))));
		Response put = requestSpec.put("/maps/api/place/update/json");
		String value2 = put.then().extract().asPrettyString();
		System.out.println(value2);
		
		
		
		
	}

}
