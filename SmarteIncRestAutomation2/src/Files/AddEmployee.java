package Files;


import static io.restassured.RestAssured.given;



import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddEmployee {

	String ID;

	@Test(dataProvider = "AddEmployeeData")
	public void addemployee(String name, String salary) {
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		String response = given().log().all().header("Content-Type", "application/json").body(Payload.AppEmpPara(name,salary)).when()
				.post("/api/v1/create").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = ReusableMethods.rawToJsonAdd(response);
		String ID = js.getString("data.id");
		System.out.println("Employee ID is : " + ID);
		this.ID = ID;
		
		

	}

	@Test(dependsOnMethods = "addemployee")
	public void UpdateEmployee() {
		String ExpectedName = "Ganesh S";
		String UpdateResponse = given().log().all().header("Content-Type", "application/json").pathParam("id", this.ID)
				.body("{\r\n" + "    \"name\":\"Ganesh S\",\r\n" + "    \"salary\":\"21000\",\r\n"
						+ "    \"age\":\"33\"\r\n" + "}")
				.when().put("/api/v1/update/{id}").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(UpdateResponse);
		JsonPath js1 = ReusableMethods.rawToJson(UpdateResponse);
		String UpdatedName = js1.getString("data.name");
		System.out.println(UpdatedName);
		Assert.assertEquals(UpdatedName, ExpectedName);

	}

	@Test(dependsOnMethods = "UpdateEmployee")
	public void GetEmployee() {
		given().log().all().header("Content-Type", "application/json").pathParam("id", this.ID).when()
				.get("/api/v1/employee/{id}").then().log().all().assertThat().statusCode(200);

	}

	@Test(dependsOnMethods = "GetEmployee")
	public void GetEmployees() {
		given().log().all().header("Content-Type", "application/json").when().get("/api/v1/employees").then().log()
				.all().assertThat().statusCode(200);

	}

	@Test(dependsOnMethods = "GetEmployees")
	public void DeleteEmployee() {
		given().log().all().header("Content-Type", "application/json").pathParam("id", this.ID).when()
				.delete("/api/v1/delete/{id}").then().log().all().assertThat().statusCode(200);
		
	}
	
	@DataProvider(name = "AddEmployeeData")
	public Object[][] getdata()
	{
		return new Object[][] {{"Ganu","15000"},{"Mahadev","856867"},{"Ranjana","67766"},{"OVI","67766"}};
		
	
}
}
