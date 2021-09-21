package Files;


import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 = new JsonPath(response);
		return js1;
				
	}
	public static JsonPath rawToJsonAdd(String response)
	{
		JsonPath js = new JsonPath(response);
		return js;
				
	}
	

}
