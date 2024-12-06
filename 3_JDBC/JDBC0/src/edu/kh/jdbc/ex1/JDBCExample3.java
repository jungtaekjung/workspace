package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample3 {

	public static void main(String[] args) {
		
		// 입력 받은 최소 급여 보다 많이 받고(이상)
	      // 입력 받은 최고 급여보단 적게 받는(이하)
	      // 사원의  사번, 이름, 급여를 급여 내림차순 조회
	      
	      // [실행화면]
	      // 최소 급여 : 1000000
	      // 최대 급여 : 3000000
	      
	      // (사번) / (이름) / (급여)
	      // (사번) / (이름) / (급여)
	      // (사번) / (이름) / (급여)

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// [2단계] : 참조변수에 알맞은 객체 대입하기
			
			// 2-1. DB 연결에 필요한 Oracle JDBC Driver 메모리 로드하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2-2. 연결 정보를 담은 Connection을 생성
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버가 thin 타입
			String ip = "localhost"; // DB 서버 컴퓨터 IP
			String port = ":1521";
			String sid = ":XE"; // DB이름
			String user = "KH_JTJ"; // 사용자명
			String pw = "KH1234"; // 비밀번호
			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("최소 급여 입력 : ");
			int min = sc.nextInt();
			System.out.print("최대 급여 입력 : ");
			int max = sc.nextInt();
			String sql = "SELECT EMP_ID, EMP_NAME,SALARY\r\n"
					+ "FROM EMPLOYEE\r\n"
					+ "WHERE SALARY BETWEEN "+min+" AND "+max+"\r\n"
					+ "ORDER BY SALARY DESC";
		
			// 4. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 5. SQL을 Statement에 적재 후 DB로 전달하여 수행한 후
			// 	  결과를 반환 받아와 rs 변수에 대입
			
			rs = stmt.executeQuery(sql);
			
			
			
			while(rs.next()) {
			int empId = rs.getInt("EMP_ID");
			String empName = rs.getString("EMP_NAME");
			int salary = rs.getInt("SALARY");
			
			// 조회 결과 출력
			System.out.printf("사번 : %d 이름 : %s 급여 : %d \n",
								empId,empName,salary);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			
			System.out.println("OJDBC 라이브러리 미등록 또는 경로 오타");
		} finally {
			
			// [4단계] 사용한 JDBC 객체 자원 반환(close)
			
		 try {
			 // NullPointerException 방지를 위해 if문 추가
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
