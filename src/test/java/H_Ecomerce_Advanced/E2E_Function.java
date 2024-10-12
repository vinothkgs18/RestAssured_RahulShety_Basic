package H_Ecomerce_Advanced;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import H_Ecomerce_Advanced_Pojo.LoginReq;
import H_Ecomerce_Advanced_Pojo.LoginRes;
import H_Ecomerce_Advanced_Pojo.Order;
import H_Ecomerce_Advanced_Pojo.OrderDetail;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class E2E_Function {
	public static void main(String[] args) {
		RestAssured restAssured= new RestAssured();
		
		//SpecBuilder
		RequestSpecBuilder requestSpecBuilder= new RequestSpecBuilder();
		 RequestSpecification requestSpecification = requestSpecBuilder.setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		
		//Serialization 
		LoginReq lr= new LoginReq();
		lr.setUserEmail("vinothkgs18.01@gmail.com");
		lr.setUserPassword("Spring*123");
		
		//Get Token	
		RequestSpecification given = restAssured.given().spec(requestSpecification).body(lr);
		LoginRes as = given.when().post("api/ecom/auth/login").as(LoginRes.class);
		String userId = as.getUserId();
		String token = as.getToken();
		
		
		
	
		//Create Product
		RequestSpecification requestSpecification2 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", token).build();
		
		RequestSpecification rs1 = restAssured.given().spec(requestSpecification2)
		.param("productName", "Sony camera")
		.param("productAddedBy",userId )
		.param("productCategory", "Electonics")
		.param("productSubCategory", "Camera")
		.param("productPrice", "25750")
		.param("productDescription", "DSLR Camera with lense")
		.param("productFor", "All")
		.multiPart("productImage",new File("C:\\Users\\vinot\\Downloads\\camera.jpeg"));
		
		Response post = rs1.when().post("api/ecom/product/add-product");
		
		String value1 = post.then().extract().asPrettyString();
		JsonPath jp= new JsonPath(value1);
		String productId = jp.getString("productId");
		
		System.out.println(value1);
		
		//create Order
		
		//serialization
		OrderDetail od= new OrderDetail();
		od.setCountry("India");
		od.setProductOrderedId(productId);
		List<OrderDetail>li=new ArrayList<OrderDetail>();
		li.add(od);
		Order or=new Order();
		or.setOrders(li);
		
		RequestSpecification requestSpecification3 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).addHeader("Authorization", token).build();
		
		RequestSpecification body = restAssured.given().spec(requestSpecification3).body(or);
		
		Response post2 = body.when().post("api/ecom/order/create-order");
		
		String value2 = post2.then().extract().asPrettyString();
		System.out.println(value2);
		JsonPath jp1=new JsonPath(value2);
		String productOrderId = jp1.getString("orders.get(0)");
		System.out.println(productOrderId);
		
		
		//Delete Order
		
		RequestSpecification pathParam = restAssured.given().spec(requestSpecification3).pathParam("Pid", productOrderId);
		
		Response delete = pathParam.when().delete("api/ecom/order/delete-order/{Pid}");
		
		String deleteValue = delete.then().extract().asPrettyString();
		int response = delete.then().extract().response().statusCode();
		System.out.println("------------------------Delete Order-----------------------------------");
		System.out.println(deleteValue);
		System.out.println(response);
		
		//Get Deleted item
		
		System.out.println("GET the deleteed product");
		
		RequestSpecification queryParam = restAssured.given().spec(requestSpecification3).queryParam("id", productOrderId);
		Response response2 = queryParam.when().get("api/ecom/order/get-orders-details");
		
		int statusCode = response2.then().extract().response().statusCode();
		
		String string = response2.then().extract().asPrettyString();
		
		System.out.println(statusCode);
		System.out.println(string);
		
		
		
		
		
		
		
		


}
	
}
