package codingChallengeXIBIX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONService {
	
	private File inputFile;
	
	
	public JSONService(File inputFile) {
	this.inputFile = inputFile;
}

	public JSONObject readJSONFile() throws ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject JSONObject = null;
		try (FileReader reader = new FileReader(inputFile)){
			
			JSONObject = (JSONObject) jsonParser.parse(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return JSONObject;
	}
	
	public static void parseElementObject() {
		
	}
}
