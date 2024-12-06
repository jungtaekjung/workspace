package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {

		// 부서명을 입력받아
		// 해당 부서에 근무하는 모든 사원의
		// 사번, 이름, 부서명, 직급명을 직급코드 오름차순 조회

		// 만약 해당 부서가 존재하지 않는다면
		// 일치하는 부서가 없습니다. 출력



		Connection conn = null; // DB 연결 정보를 저장한 객체
		Statement stmt = null; // DB SQL 수행 -> 결과를 반환 받는 객체
		ResultSet rs = null; // SELECT 결과를 저장하는 객체

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
			System.out.print("부서명: ");
			String dept = sc.next();

			// 입력 받은 문자열이 저장된 변수를 sql에 추가할 때
			// 양쪽에 홑따옴표('')반드시 작성!!
			String sql = "SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME\r\n"
					+"FROM EMPLOYEE\r\n"
					+"LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)\r\n"
					+"JOIN JOB USING (JOB_CODE)\r\n"
					+"WHERE DEPT_TITLE =  '"+dept+"'\r\n"
					+"ORDER BY JOB_CODE";

			// 4. Statement 객체 생성
			stmt = conn.createStatement();

			// 5. SQL을 Statement에 적재 후 DB로 전달하여 수행한 후
			// 	  결과를 반환 받아와 rs 변수에 대입

			rs = stmt.executeQuery(sql);

			boolean flag = true; // true이면 조회 결과 없음, false 면 있음

			while(rs.next()) {

				flag = false; // while문이 한 번이라도 수행되면 false 변경

				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String deptName = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");

				// 조회 결과 출력
				System.out.printf("사번 : %d 이름 : %s 부서명 : %s 직급명 : %s \n",
						empId,empName,deptName,jobName);
			}
			if(flag) { // while문 종료 후 flag==true -> 조회 결과 없음
				System.out.println("해당 부서가 없습니다");
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
