package B_Library;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetBook {
	public static void main(String[] args) {
		RestAssured.baseURI="http://216.10.245.166";
		RequestSpecification requestSpecification = RestAssured.given().queryParam("AuthorName", "g");
		Response response = requestSpecification.when().get("/Library/GetBook.php");
		String string = response.then().extract().asPrettyString();
		System.out.println(string);
		
	}

}
