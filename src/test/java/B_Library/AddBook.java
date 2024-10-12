package B_Library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddBook {
	public static void main(String[] args) throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		RequestSpecification requestSpecification = RestAssured.given().body(new String(Files.readAllBytes(Paths.get("src/test/java/B_Library/payload.json"))));
		Response response = requestSpecification.when().post("/Library/Addbook.php");
		String string = response.then().extract().asPrettyString();
		System.out.println(string);
	}
	
	

}
