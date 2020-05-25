package application;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadKFC {
	
	public static void main(String argv[]) {
		
		JSONParser parser = new JSONParser();
		
	try(FileReader reader = new FileReader("kfc.json"))
	{
		// read
		Object obj = parser.parse(reader);
		JSONArray userList = (JSONArray) obj;
		// System.out.println(userList);
		// iterate
		userList.forEach(user -> parseUserObject((JSONObject)user));
	}	
			
		catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		catch(ParseException e) {e.printStackTrace();}
		catch(Exception e) {e.printStackTrace();}
	}
	
	static String testUser = "anca";
	static String testPass = "1234";
	
	static boolean foundUser = false;
	static boolean foundPass = false;
	private static void parseUserObject(JSONObject user)
	{
		JSONObject userObj = (JSONObject) user.get("user");
		/// get username, password, user type
		String readUser = (String) userObj.get("username");
		String readPass = (String) userObj.get("password");
		String readType = (String) userObj.get("type");
		
		if(testUser.equals(readUser)) {
			if(testPass.equals(readPass) == false)
			{
				foundPass = true;
			}
			return;
		}
		
			
		//System.out.println(readUser + readPass + readType);
	}
}
