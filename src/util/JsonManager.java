package util;

import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class JsonManager {
	
	public static Map<String,String> getMap(String data) {
		JSONObject j = new JSONObject(data);
		Map<String, Object> map = j.toMap();
		
		Map<String, String> newMap = map.entrySet().stream()
			     .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
		
		return newMap;
	}
	
	public static String getJSONString(Map<String,String> data) {
		JSONObject j = new JSONObject(data);
		return j.toString();
	}
	
	
}
