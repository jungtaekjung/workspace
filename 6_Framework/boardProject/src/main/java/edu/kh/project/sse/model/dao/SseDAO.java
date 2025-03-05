package edu.kh.project.sse.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.sse.model.dto.Notification;

@Repository
public class SseDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	// 알림 삽입
	public int insertNotification(Notification notification) {
		return sqlSession.insert("sseMapper.insertNotification",notification);
	}


	
	/** 알림을 받아야 하는 회원 + 안읽은 알림 개수 조회
	 * @param notificationNo
	 * @return map
	 */
	public Map<String, Object> selectReceiveMember(int notificationNo) {
		return sqlSession.selectOne("sseMapper.selectReceiveMember",notificationNo);
	}



	public List<Notification> selectNotificationList(int memberNo) {
		return sqlSession.selectList("sseMapper.selectNotificationList",memberNo);
	}



	/** 현재 로그인한 회원이 받은 알림 중 읽지 않은 알림 개수 조회
	 * @param memberNo
	 * @return
	 */
	public int notReadCheck(int memberNo) {
		return sqlSession.selectOne("sseMapper.notReadCheck",memberNo);
	}



	public void deleteNotification(int notificationNo) {
		 sqlSession.delete("sseMapper.deleteNotification",notificationNo);
	}



	public void readNotification(int notificationNo) {
		sqlSession.update("sseMapper.readNotification",notificationNo);
		
	}

}
