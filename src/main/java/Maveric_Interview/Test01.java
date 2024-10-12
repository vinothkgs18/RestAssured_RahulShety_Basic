package Maveric_Interview;

import io.restassured.path.json.JsonPath;

public class Test01 {
	public static void main(String[] args) {
	String a="{\"employees\":[    \r\n"
			+ "    {\"name\":\"Tony\", \"email\":\"tony@gmail.com\", \"age\":23},    \r\n"
			+ "    {\"name\":\"Joe\", \"email\":\"joe@gmail.com\", \"age\":28},  \r\n"
			+ "    {\"name\":\"Marina\", \"email\":\"marina@gmail.com\", \"age\":33},    \r\n"
			+ "    {\"name\":\"Kathy\", \"email\":\"kathy@gmail.com\", \"age\":41}   \r\n"
			+ "]}";
	JsonPath jp= new JsonPath(a);
	int int1 = jp.getInt("employees.size()");
	System.out.println(int1);
	System.out.println(jp.getString("employees.get(0).name"));
	for(int i=0;i<int1;i++) {
		if(jp.getString("employees.get("+i+").name").equals("Tony")) {
			String string = jp.getString("employees.get("+i+").email");
			System.out.println(string);
		}
	}
	
		
	}

}
