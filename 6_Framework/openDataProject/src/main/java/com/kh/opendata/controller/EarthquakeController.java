package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class EarthquakeController {
	
	public static final String SERVICEKEY="0W24KI3V468HM929";

	@ResponseBody   
	@RequestMapping(value="earthquake", produces="text/xml; charset=UTF-8")
	public String earthquakeXML(String location) throws IOException{

		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url ="https://www.safetydata.go.kr/V2/api/DSSP-IF-10944";
		url +="?serviceKey="+SERVICEKEY; // 서비스키 추가
		url +="&numOfRows=100"; // 지역명 추가(한글 들어가면 인코딩 처리)
		url +="&returnType=xml"; // 리턴 타입


		// 1. 작성된 url 정보를 넣어 URL 객체 생성
		URL requestUrl = new URL(url);

		// 2. 생성된 URL 객체를 가지고 HttpUrlConnection 객체 얻어내기
		HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();

		// 3. 요청 시 필요한 Header 설정하기
		urlConn.setRequestMethod("GET");

		// 4. 해당 OpenAPI 서버로 요청 보낸 후 입력스트림을 통해 응답데이터 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

		String line;
		String responseText="";

		while((line = br.readLine())!=null) { //한줄씩 읽어올 데이터가 있는 동안 반복
			responseText +=line;
		}
		//5. 다 사용한 스트림 객체 반납하기
		br.close();
		urlConn.disconnect();

		return responseText;
	}

}
