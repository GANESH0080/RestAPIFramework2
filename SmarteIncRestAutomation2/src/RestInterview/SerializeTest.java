package RestInterview;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBodyExtractionOptions;

public class SerializeTest {
	public static void main(String args[])
	{
	RestAssured.baseURI = "http://dummy.restapiexample.com";
		
	AddEmployeeSerialize a = new AddEmployeeSerialize();
	a.setName("Ganusalunkhe");
	a.setSalary("2334");
	a.setAge(22);
			
	String res = given().log().all().header("Content-Type", "application/json").body(a)
			.when().post("/api/v1/create").then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(res);
	JsonPath js = new JsonPath(res);
	String ID = js.getString("data.id");
	System.out.println(ID);
	
	
	AddEmployeeSerialize get = ((ResponseBodyExtractionOptions) given().log().all().header("Content-Type", "application/json").pathParam("id", ID).expect().defaultParser(Parser.JSON).when()
	.get("/api/v1/employee/{id}").then().log().all().assertThat().statusCode(200)).as(AddEmployeeSerialize.class);
	System.out.println(get.getName());
	System.out.println(get.getSalary());
	System.out.println(get.getAge());
	//.then().log().all().assertThat().statusCode(200);
	

	
	
	

}
}