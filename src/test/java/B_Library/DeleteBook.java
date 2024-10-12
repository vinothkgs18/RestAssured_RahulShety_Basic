package B_Library;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBook {
	public static void main(String[] args) {

		RestAssured.baseURI = "http://216.10.245.166";
		RequestSpecification requestSpecification = RestAssured.given()
				.body("{\r\n" + " \r\n" + "\"ID\" : \"Eng1403\"\r\n" + " \r\n" + "} \r\n" + "");
		Response response = requestSpecification.when().post("/Library/DeleteBook.php");
		String string = response.then().extract().asPrettyString();
		System.out.println(string);

	}
}