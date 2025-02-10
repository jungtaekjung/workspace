package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.dto.Air;

@Controller 
public class OpenAPIController {

	// 발급받은 인증키 변수처리
	public static final String SERVICEKEY="d6VZaEmHDfOtArmos3Q0rMFO9DVE78QtAyro%2FrvdHkySAW8WKGH9y0KpP2jcD%2BbEk%2BFE5GkaxilnKmMT0y402A%3D%3D";


	// json 형식으로 대기오염 OpenAPI 활용
	@ResponseBody   
	//@RequestMapping(value="air", produces="application/json; charset=UTF-8")
	public String airPollution(String location) throws IOException{

		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url ="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url +="?serviceKey="+SERVICEKEY; // 서비스키 추가
		url +="&sidoName="+ URLEncoder.encode(location,"UTF-8"); // 지역명 추가(한글 들어가면 인코딩 처리)
		url +="&returnType=json"; // 리턴 타입


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

	// xml 형식으로 대기오염 OpenAPI 활용
	@ResponseBody   
	@RequestMapping(value="air", produces="text/xml; charset=UTF-8")
	public String airPollutionXML(String location) throws IOException{

		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url ="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url +="?serviceKey="+SERVICEKEY; // 서비스키 추가
		url +="&sidoName="+ URLEncoder.encode(location,"UTF-8"); // 지역명 추가(한글 들어가면 인코딩 처리)
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
	
	@ResponseBody   
	@RequestMapping(value="earthquake", produces="text/xml; charset=UTF-8")
	public String earthquakeXML(String location) throws IOException{

		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url ="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url +="?serviceKey="+SERVICEKEY; // 서비스키 추가
		url +="&sidoName="+ URLEncoder.encode(location,"UTF-8"); // 지역명 추가(한글 들어가면 인코딩 처리)
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
