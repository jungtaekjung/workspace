package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	private static final String serviceKey = "d6VZaEmHDfOtArmos3Q0rMFO9DVE78QtAyro%2FrvdHkySAW8WKGH9y0KpP2jcD%2BbEk%2BFE5GkaxilnKmMT0y402A%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="busroute.do", produces="application/json; charset=UTF-8")
	public String test(String location) throws IOException{
		
		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
		
		url += "?ServiceKey="+serviceKey;
		url += "&strSrch=3";
		url += "&returnType=json";

		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) !=null) {
			responseText += line;
		}
		br.close();
		urlConnection.disconnect();
		return responseText;
	}
}
