package util;

import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class JsonManager {
	
	public static Map<String,String> getMap(String data) {
		var j = new JSONObject(data);
		var map = j.toMap();
		
		var newMap = map.entrySet().stream()
			     .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
		
		return newMap;
	}
	
	public static String getJSONString(Map<String,String> data) {
		var j = new JSONObject(data);
		return j.toString();
	}
	
	
}
