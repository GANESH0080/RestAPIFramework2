package RestInterview;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestTestAddEMP {

	public static void main(String[] args) {
		// Validate Add Employee API
		// Given - All input details
		// When - Submit the API
		// Then - Validate the response
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AppEmp()).when()
				.post("/api/v1/create").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = ReusableMethods.rawToJsonAdd(response);
		String ID = js.getString("data.id");

		// Update Employee
		String ExpectedName = "Ganesh S";
		String UpdateResponse = given().log().all().header("Content-Type", "application/json").pathParam("id", ID)
				.body("{\r\n" + "    \"name\":\"Ganesh S\",\r\n" + "    \"salary\":\"21000\",\r\n"
						+ "    \"age\":\"33\"\r\n" + "}")
				.when().put("/api/v1/update/{id}").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(UpdateResponse);
		JsonPath js1 = ReusableMethods.rawToJson(UpdateResponse);
		String UpdatedName = js1.getString("data.name");
		System.out.println(UpdatedName);
		Assert.assertEquals(UpdatedName, ExpectedName);

		// Get Employee with ID
		given().log().all().header("Content-Type", "application/json").pathParam("id", ID).when()
				.get("/api/v1/employee/{id}").then().log().all().assertThat().statusCode(200);

		// Get All empoyeeData
		given().log().all().header("Content-Type", "application/json").when().get("/api/v1/employees").then().log()
				.all().assertThat().statusCode(200);

		// Delete employee data
		given().log().all().header("Content-Type", "application/json").pathParam("id", ID).when()
				.delete("/api/v1/delete/{id}").then().log().all().assertThat().statusCode(200);

	}

}
