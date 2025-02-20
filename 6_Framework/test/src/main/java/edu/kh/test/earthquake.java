package edu.kh.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class earthquake {

	public static final String serviceKey = "GL9B5GMJ23944072";
	
	@RequestMapping(value="earthquake", produces="text/xml; charset=UTF-8")
	public String earthquake() throws IOException{
		
		String url = "https://www.safetydata.go.kr/V2/api/DSSP-IF-10944";
		url += "?serviceKey=" + serviceKey;
		url += "&numOfRows=2";
		url += "&returnType=xml";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String line = null;
		String text ="";
		while((line = br.readLine()) != null) {
			text += line;
		}
		br.close();
		urlConnection.disconnect();
		
		return text;
	}
}
