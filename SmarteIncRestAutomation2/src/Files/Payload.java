package Files;

public class Payload {

	
	public static String AppEmpPara(String name, String salary)
	{
		
		return "{\\r\\n\" + \r\n" + 
				"	    		\"    \\\"name\\\":\\\""+name+"\\\",\\r\\n\" + \r\n" + 
				"	    		\"    \\\"salary\\\":\\\""+salary+"\\\",\\r\\n\" + \r\n" + 
				"	    		\"    \\\"age\\\":\\\"32\\\"\\r\\n\" + \r\n" + 
				"	    		\"}\"";
	}
	public static String AppEmp()
	{
		
		return "{\\r\\n\" + \r\n" + 
				"	    		\"    \\\"name\\\":\\\"Ganu\\\",\\r\\n\" + \r\n" + 
				"	    		\"    \\\"salary\\\":\\\"764758\\\",\\r\\n\" + \r\n" + 
				"	    		\"    \\\"age\\\":\\\"32\\\"\\r\\n\" + \r\n" + 
				"	    		\"}\"";
	}
	
}