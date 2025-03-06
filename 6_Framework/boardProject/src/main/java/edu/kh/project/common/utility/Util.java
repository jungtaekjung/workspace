package edu.kh.project.common.utility;

import java.text.SimpleDateFormat;

public class Util {
	
	// Cros Site Scripting(XSS) 방지 처리
	// - 권한이 없는 사용자가 사이트에 스크립트를 작성하는 것
	// - 웹 애플리케이션에서 발생하는 취약점
	
	
	//개행 문자 -> ><br> 변경 메소드
	public static String newLineHandling(String content) {
		return content.replaceAll("(\r\n|\n\r|\n|\r)", "<br>");
		
		//textarea 의 엔터 : \r\n
		// \r : 캐리지 리턴(첫 번째로 돌아가기)
		// \n : new line(다음줄로 이동)
	}
	

	//XSS(Cross Site Scripting) : 관리자가 아닌 이용자가 악성 스크립트를 삽입해서 공격
	
	//XSS 공격 방지 처리 메소드
	
	public static String XSSHandling(String content) {
		// <,>, &, " 문자를 HTML코드가 아닌 문자 그대로 보이도록 변경
		
		content = content.replaceAll("&", "&amp"); //1빠여야함
		content = content.replaceAll("<", "&lt");
		content = content.replaceAll(">", "&gt");
		content = content.replaceAll("\"", "&quot");
		content = content.replaceAll("&ltstrong&gt", "<strong>");
		content = content.replaceAll("&lt/strong&gt", "</strong>");
		content = content.replaceAll("\r", "<br>");

		
		return content;
	}
	
	// 파일명 변경 메소드
    public static String fileRename(String originFileName) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
       String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

       int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

       String str = "_" + String.format("%05d", ranNum);

       String ext = originFileName.substring(originFileName.lastIndexOf("."));

       return date + str + ext;
    }

	

}
