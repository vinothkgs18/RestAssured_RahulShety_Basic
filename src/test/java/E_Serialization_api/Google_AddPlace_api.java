package E_Serialization_api;

import java.util.ArrayList;
import java.util.List;

import E_Serialization_Pojo.AddPlace;
import E_Serialization_Pojo.Location;
import E_Serialization_Pojo.ResponsePojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Google_AddPlace_api {
	
	public static void main(String[] args) {
		
		//creating object for main Pojo class
		AddPlace ap= new AddPlace();
		
		//creating object for sub Pojo class
		Location lc= new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		//adding element into ist
		List<String>li= new ArrayList();
		li.add("Car showroom");
		li.add("Bike Showroom");
		
		
		//set the value for all variable
		ap.setLocation(lc);
		ap.setAccuracy(50);
		ap.setName("Vinoth Kumar");
		ap.setPhone_number("9597359037");
		ap.setAddress("2/8 Valmigi street Sembakkam");
		ap.setTypes(li);
		ap.setWebsite("https://vinothsoft.com");
		ap.setLanguage("English");
			
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		RestAssured restAssured=new RestAssured();
		RequestSpecification requestSpecification = restAssured.given().queryParam("key", "qaclick123").body(ap);
		Response response = requestSpecification.when().post("/maps/api/place/add/json");
		ResponsePojo as = response.as(ResponsePojo.class);
		System.out.println(as.getStatus());
		System.out.println(as.getPlace_id());
		
	}

}
