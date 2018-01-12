package lotto;
/*
 * GpsToAddress - GPS 위도 경도로 한글 주소로 변환 - google maps API
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

class GpsToAddress {
	double latitude;
	double longitude;
	String regionAddress;
	String location;
	
	public GpsToAddress(double latitude, double longitude) throws Exception {
		this.latitude = latitude;
		this.longitude = longitude;
		this.regionAddress = getRegionAddress(getJSONData(getApiAddress()));
	}
	
	public GpsToAddress(String location) throws Exception {
		this.location = location;
		this.regionAddress = getGps(getJSONData(getApiGps()));
	}

	private String getApiAddress() {
		String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
				+ latitude + "," + longitude + "&language=ko&key=AIzaSyDLD7yIiCNnlZWCDG6vv1JKH9fpxJVtUTw";
		return apiURL;
	}
	private String getApiGps() {
		try {location = URLEncoder.encode(location, "UTF-8");
		} catch (UnsupportedEncodingException e) {}
		String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?address="
				+ location
				+ "&region=kr&key=AIzaSyDLD7yIiCNnlZWCDG6vv1JKH9fpxJVtUTw";
		return apiURL;
	}

	private String getJSONData(String apiURL) throws Exception {
		String jsonString = new String();
		String buf;
		URL url = new URL(apiURL);
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		while ((buf = br.readLine()) != null) {
			jsonString += buf;
		}
		return jsonString;
	}

	private String getRegionAddress(String jsonString) {
		JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
		JSONArray jArray = (JSONArray) jObj.get("results");
		jObj = (JSONObject) jArray.get(0);
		return (String) jObj.get("formatted_address");
	}
	private String getGps(String jsonString) {
		JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
		JSONArray jArray = (JSONArray) jObj.get("results");
		jObj = (JSONObject) jArray.get(0);
		jObj = (JSONObject) jObj.get("geometry");
		jObj = (JSONObject) jObj.get("location");
		Double lng = (Double) jObj.get("lng");
		Double lat = (Double) jObj.get("lat");
		return lat+","+lng;
	}

	public String getAddress() {
		return regionAddress;
	}
}
public class Geocoding {
	public String city(double latitude, double longitude) throws Exception {
		GpsToAddress gps = new GpsToAddress(latitude, longitude);
		return gps.getAddress();
	}
	public String latlng(String location) throws Exception {
		GpsToAddress gps = new GpsToAddress(location);
		return gps.getAddress();
	}
	public static void main(String[] args) throws Exception {
		Geocoding geo = new Geocoding();
		System.out.println(geo.latlng("부산"));
		Double a,b;
		a= Double.parseDouble(geo.latlng("부산").split(",")[0]);
		b= Double.parseDouble(geo.latlng("부산").split(",")[1]);
		System.out.println(geo.city(a, b));
	}
}