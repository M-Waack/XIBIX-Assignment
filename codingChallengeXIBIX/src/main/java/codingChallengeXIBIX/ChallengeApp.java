package codingChallengeXIBIX;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ChallengeApp {

	public static void main(String[] args) throws ParseException {
		
		File inputJSON = new File(args[0]);
		int n = Integer.parseInt(args[1]);
		
		JSONService jsonService = new JSONService(inputJSON);
		JSONObject jsonObject = jsonService.readJSONFile();
		
		JSONArray elements = (JSONArray) jsonObject.get("elements");
		JSONArray values = (JSONArray) jsonObject.get("values");
		
		ArrayList<JSONObject> valueList = new ArrayList<>();
		for (int i = 0; i < values.size(); i++) {
            valueList.add((JSONObject) values.get(i));
        }
		
		HashMap<Long, ArrayList<Integer>> elementMap = new HashMap<Long, ArrayList<Integer>>();
		for (int i = 0; i < elements.size(); i++) {
			JSONObject element = (JSONObject) elements.get(i);
			JSONArray nodes = (JSONArray) element.get("nodes");
			elementMap.put((Long) element.get("id") , (ArrayList<Integer>) nodes);
		}
		
        Collections.sort(valueList, new MyJSONComparator());
        
        ArrayList<JSONObject> resultList = new ArrayList<>();
        int i = 0;
        while (resultList.size() < n) {
        	if (valueList.get(i).get("value").equals(valueList.get(i + 1).get("value")) ) {
        		ArrayList<Integer> list1 = elementMap.get(valueList.get(i).get("element_id"));
        		ArrayList<Integer> list2 = elementMap.get(valueList.get(i + 1).get("element_id"));
        		if (!areElementsNeighbors(list1, list2)) {
        			resultList.add(valueList.get(i));
        		}
        	} else {
        		resultList.add(valueList.get(i));
        	}
        	i++;
        }
        printResult(resultList);
	}
	public static boolean areElementsNeighbors (ArrayList<Integer> list1, ArrayList<Integer> list2) {
		
		Set<Integer> intersect = new HashSet<Integer>(list1);
	    intersect.retainAll(list2);
	    if (intersect.size() == 2) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	public static void printResult(ArrayList<JSONObject> resultList) {
		System.out.println("[");
		for (JSONObject obj : resultList) {
			System.out.println("\t{element_id: " + obj.get("element_id") + ", value: " + obj.get("value") + "},");
		}
		System.out.println("]");
	}
}

class MyJSONComparator implements Comparator<JSONObject> {

	@Override
	public int compare(JSONObject o1, JSONObject o2) {
	    double v1 = (double) o1.get("value");
	    double v3 = (double) o2.get("value");
	    return Double.compare(v3, v1);
	}
}
