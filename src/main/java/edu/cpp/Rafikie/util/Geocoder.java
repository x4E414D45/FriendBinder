package edu.cpp.Rafikie.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Geocoder {

	public static Double[] geocode(String location) {
		Double geocode[] = new Double[2];
		String locationEncoded = URLEncoder.encode(location);
		String url = "http://api.opencagedata.com/geocode/v1/json?q=" + locationEncoded + "&key=8e871547aa2876d0451e299011ca47e9";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		try {
			HttpResponse response = client.execute(request);
			System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONArray jsonResults = new JSONObject(result.toString()).getJSONArray("results");
			JSONObject geometry = jsonResults.getJSONObject(0).getJSONObject("geometry");
			geocode[0] = Double.parseDouble(geometry.get("lat").toString());
			geocode[1] = Double.parseDouble(geometry.get("lng").toString());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return geocode;
	}
}
