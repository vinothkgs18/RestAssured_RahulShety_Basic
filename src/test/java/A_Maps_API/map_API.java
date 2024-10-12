package A_Maps_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.*;

public class map_API {

	public static void main(String[] args) {
		PayLoad p= new PayLoad();
		String newAddress="1/64 Ahraharam aruvuzhimangalam Kudavasal 612604";
		//RestAssured restAssured= new RestAssured();
		RestAssured.baseURI="https://rahulshettyacademy.com";
		RequestSpecification requestSpecification = RestAssured.given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(p.addPlace());
		Response response = requestSpecification.when().post("/maps/api/place/add/json");
		String string = response.then().extract().response().asPrettyString();
		System.out.println(string);
		
		JsonPath jp= new JsonPath(string);
		String placeId = jp.getString("place_id");
		
		System.out.println("***************************");
		System.out.println(placeId);
		
		
		
		//Update Address
		System.out.println("-----------------------PUT-----------------------------------");
		
		RequestSpecification requestSpecification1 = RestAssured.given().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+placeId+"\",\r\n" + 
						"\"address\":\""+newAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}");
		Response response1 = requestSpecification1.when().put("/maps/api/place/update/json");
		
		String asPrettyString = response1.then().extract().asPrettyString();
		
		System.out.println(asPrettyString);
		
		
		//Get Address
		System.out.println("-----------------------GET-----------------------------------");
		RequestSpecification requestSpecification2 = RestAssured.given().queryParam("key", "qaclick123").queryParam("place_id",placeId).header("Content-Type","application/json");
		Response response2 = requestSpecification2.when().get("/maps/api/place/get/json");
		String value = response2.then().extract().asPrettyString();
		JsonPath jsonPath= new JsonPath(value);
		System.out.println(jsonPath.getString("address"));
		
		
		
		
		
		
	}

}
