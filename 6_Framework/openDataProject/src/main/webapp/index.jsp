<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <!DOCTYPE html>
   <html>

   <head>
      <meta charset="UTF-8">
      <script src="https://code.jquery.com/jquery-3.7.1.min.js"
         integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
      <title>대기오염 공공데이터</title>

   </head>

   <body>
      <h1>실시간 대기오염 정보</h1>

      지역 :
      <select id="location">
         <option>서울</option>
         <option>부산</option>
         <option>대구</option>
      </select>

      <button id="btn1">해당 지역 대기 오염 정보</button>
      <br>
      <br>

      <table border="1" id="result1">
         <thead>
            <tr>
               <th>측정소명</th>
               <th>측정일시</th>
               <th>통합대기환경수치</th>
               <th>미세먼지농도</th>
               <th>아황산가스농도</th>
               <th>일산화탄소농도</th>
               <th>이산화탄소농도</th>
               <th>오존농도</th>
            </tr>




         </thead>
         <tbody></tbody>
      </table>


      <script>
         $(function () {
            $("#btn1").click(function () {
            //-------------------------------------------------------
            // json 형식으로 응답 받을 때
         
            
            <% --
                  $.ajax({
                     url: "air",
                     data: { location: $("#location").val() },
                     success: function (data) {
                        const result1 = document.getElementById("result1");
                        console.log(data.response.body.items);

                        const itemArr = data.response.body.items;
                        let value = "";
                        for (let item of itemArr) {
                           value += "<tr>" +
                              "<td>" + item.stationName + "</td>" +
                              "<td>" + item.dataTime + "</td>" +
                              "<td>" + item.khaiValue + "</td>" +
                              "<td>" + item.pm10Value + "</td>" +
                              "<td>" + item.so2Value + "</td>" +
                              "<td>" + item.coValue + "</td>" +
                              "<td>" + item.no2Value + "</td>" +
                              "<td>" + item.o3Value + "</td>" +
                              "</tr>";
                        }
                        $("#result1 > tbody").html(value);


                        for (let item of data.response.body.items) {
                           const tr = document.createElement("tr");
                           const td1 = document.createElement("td");
                           const td2 = document.createElement("td");
                           const td3 = document.createElement("td");
                           const td4 = document.createElement("td");
                           const td5 = document.createElement("td");
                           const td6 = document.createElement("td");
                           const td7 = document.createElement("td");
                           const td8 = document.createElement("td");

                           td1.innerText = item.stationName;
                           td2.innerText = item.dataTime;
                           td3.innerText = item.khaiValue;
                           td4.innerText = item.pm10Value;
                           td5.innerText = item.so2Value;
                           td6.innerText = item.coValue;
                           td7.innerText = item.no2Value;
                           td8.innerText = item.o3Value;
                           console.log(item.coGrade);
                           tr.append(td1, td2, td3, td4, td5, td6, td7, td8);
                           result1.append(tr);
                        }


                     },
                     error: function () { console.log("통신 실패") }
                  }) --%>



                     //-------------------------------------------
                     // xml 형식으로 응답 받을 때
                     $.ajax({
                        url: "air",
                        data: { location: $("#location").val() },
                        success: function (data) {
                           console.log(data)

                           //$('요소명').find(매개변수)
                           // - 기준이 되는 요소의 하위 요소들 중 특정 요소를 찾을 때 사용
                           // - html, xml은 같은 markup language이기 때문에 사용 가능함
                           //console.log($(data).find("item"))

                           //xml 형식의 응답 데이터를 받았을 때
                           //1. 넘겨받은 데이터를 $() 제이쿼리화 시킨 후
                           //    응답데이터 안에 실제 데이터가 담겨있는 요소 선택
                           const itemArr = $(data).find("item")

                           //2. 반복문을 통해 실제 데이터가 담긴 요소에 접근해서 요소 만들기
                           let value = "";
                           itemArr.each(function (index, item) {
                              //console.log(item)
                              //console.log(index)
                              console.log($(item).find("stationName").text())
                              value += "<tr>" +
                                 "<td>" + $(item).find("stationName").text() + "</td>" +
                                 "<td>" + $(item).find("dataTime").text() + "</td>" +
                                 "<td>" + $(item).find("khaiValue").text() + "</td>" +
                                 "<td>" + $(item).find("pm10Value").text() + "</td>" +
                                 "<td>" + $(item).find("so2Value").text() + "</td>" +
                                 "<td>" + $(item).find("coValue").text() + "</td>" +
                                 "<td>" + $(item).find("no2Value").text() + "</td>" +
                                 "<td>" + $(item).find("o3Value").text() + "</td>" +
                                 "</tr>";
                           })

                           //3. 만들어낸 요소를 화면에 출력
                           $("#result1 > tbody").html(value);
                        },
                        eerror: function () { console.log("통신 에러") }
                     })

            })
         })

         $(function () {
            $("#btn1").click(function () {

            })
         })

      </script>


      <hr>

      <h1>실시간 지진해일 긴급대피장소</h1>

      <button id="btn2">실시간 지진해일 대피소 정보</button>
      <br><br>

      <table border="1" id="result2">
         <thead>
            <tr>
               <th>시도명</th>
               <th>시군구명</th>
               <th>대피지구명</th>
               <th>대피장소명</th>
               <th>주소</th>
               <th>경도</th>
               <th>위도</th>
               <th>수용가능인원수</th>
               <th>대피소 분류명</th>
            </tr>
         </thead>
         <tbody></tbody>
      </table>

      공공데이터사이트에 행정안전부_지진해일 긴급대피장소 검색 후 xml 방식으로 진행




   </body>

   </html>