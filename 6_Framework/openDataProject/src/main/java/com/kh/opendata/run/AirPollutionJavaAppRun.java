package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.dto.Air;

public class AirPollutionJavaAppRun {
   
   // 발급받은 인증키 변수처리
   public static final String SERVICEKEY="d6VZaEmHDfOtArmos3Q0rMFO9DVE78QtAyro%2FrvdHkySAW8WKGH9y0KpP2jcD%2BbEk%2BFE5GkaxilnKmMT0y402A%3D%3D";
   
   public static void main(String[] args) throws IOException {
      
      // OpenAPI 서버로 요청하고자 하는 url 작성
      String url ="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
      url +="?serviceKey="+SERVICEKEY; //서비스키가 제대로 부여되지 않았을 경우
                           //SERVICE_KEY_IS_NOT_REGISTERED_ERROR 발생
      url +="&sidoName="+ URLEncoder.encode("서울","UTF-8");
      url +="&returnType=json";
      
      // System.out.println(url);
      
      // ** HttpURLConnection 객체를 활용해서 OpenAPI 요청 절차 **
      //1. 요청할 주소를 전달해서 java.net.URL 객체 생성하기
      URL requestUrl = new URL(url);
      
      //2. 생성된 URL 객체를 가지고 HttpUrlConnection 객체 얻어내기
      HttpURLConnection urlConn = (HttpURLConnection)requestUrl.openConnection();
      
      //3. 요청시 필요한 Header 설정하기
      urlConn.setRequestMethod("GET");
      
      //4. 해당 OpenAPI 서버로 요청 보낸 후 입력 스트림을 통해 응답데이터 받기
      BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
      
      String line;
      String responseText="";
      
      while((line = br.readLine())!=null) { //한줄씩 읽어올 데이터가 있는 동안 반복
      // System.out.println(line);
         responseText +=line;
      }
      
      // System.out.println(responseText);
      
      // JsonObject, JsonArray, JsonElement 이용해서 파싱 가능(gson 라이브러리)
      // => 내가 원하는 데이터만 추출 가능
      
      // json 데이터를 원하는 데이터로 추출하여 DTO에 담기
      
      // 응답 데이터 text를 JsonObject화 시키는 작업(파싱)
      JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
      //System.out.println("total : "+totalObj);
      
      // response 속성 접근
      JsonObject responseObj = totalObj.getAsJsonObject("response");
      //System.out.println("response : "+responseObj);
      
      // body 속성 접근
      JsonObject bodyObj = responseObj.getAsJsonObject("body");
      //System.out.println("body : "+bodyObj);
      
      // totalCount 속성 접근
      int totalCount = bodyObj.get("totalCount").getAsInt();
      //System.out.println("totalCount : "+totalCount);
      
      // items(JsonArray형태) 속성 접근
      JsonArray itemArr = bodyObj.getAsJsonArray("items");
      //System.out.println("itemArr : " + itemArr);
      
      // 방법1)
     /* ArrayList<Air> list = new ArrayList<Air>();
      
      
      for(int i=0; i<itemArr.size(); i++) {
    	  // items에 담겨있는 item 객체를 하나씩 추출
    	  JsonObject item = itemArr.get(i).getAsJsonObject();
    	  //System.out.println(item);
      
    	  Air air = new Air();
    	  air.setStationName(item.get("stationName").getAsString());
    	  air.setDataTime(item.get("dataTime").getAsString());
          air.setKhaiValue(item.get("khaiValue").getAsString());
          air.setPm10Value(item.get("pm10Value").getAsString());
          air.setSo2Value(item.get("so2Value").getAsString());
          air.setCoValue(item.get("coValue").getAsString());
          air.setNo2Value(item.get("no2Value").getAsString());
          air.setO3Value(item.get("o3Value").getAsString());
      
          list.add(air);
      }*/

      // 방법 2) Gson 라이브러리 이용해서 값 세팅
      Gson gson = new Gson();
      
      // JsonObject -> 배열(객체) 변환
      // 이 때, json으로 받은 데이터를 Air(DTO)에 자동으로 set 해줌 (Gson 라이브러리)
      Air[] airArr = gson.fromJson(itemArr, Air[].class);
      System.out.println("airArr : " + airArr);
      
      // 배열 -> List로 변환
      List<Air> list = Arrays.asList(airArr);
      
      
      
      
      // list에 담긴 dto 확인
      for(Air air : list) {
    	  System.out.println("air : " + air);
      }
      
      //5. 다 사용한 스트림 객체 반납하기
      br.close();
      urlConn.disconnect();

      
      
   }

}
