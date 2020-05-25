package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateKFC {

	
	public static void insertUser(String username, String password, String type) {
		
		JSONArray jsonArray = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try(Reader reader = new FileReader("kfc.json"))
		{
			jsonArray = (JSONArray) parser.parse(reader);
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject();
		obj.put("username", username);
		obj.put("password", password);
		obj.put("type", type);
		
		
		jsonArray.add(obj);
		
		try(FileWriter file = new FileWriter("kfc.json"))
		{
			file.write(jsonArray.toJSONString());
			file.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(obj);
		
	}
	
}
