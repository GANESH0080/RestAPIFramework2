package Files;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddEmpFile {
    @Test
	public void addEmp() throws IOException
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		String response = given().log().all().header("Content-Type", "application/json").body(GenerateStringFromResource("D:\\SeleniumWorkspace\\SmarteIncRestAutomation2\\AddEmpoyee.json")).when()
				.post("/api/v1/create").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = ReusableMethods.rawToJsonAdd(response);
		String ID = js.getString("data.id");
		System.out.println("Employee ID is : " + ID);
		
		
	}
	
public static String GenerateStringFromResource(String path) throws IOException
	
	{
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}
}
