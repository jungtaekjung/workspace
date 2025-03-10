package edu.kh.project.sse.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.sse.model.dto.Notification;

public interface SseService {

	/** 알림 삽입 후 알림 받을 회원 번호 + 알림 개수 반환
	 * @param notification
	 * @return map
	 */
	Map<String, Object> insertNotification(Notification notification);

	/** 알림 목록 조회
	 * @param memberNo
	 * @return notificationList
	 */
	List<Notification> selectNotificationList(int memberNo);

	/** 현재 로그인한 회원이 받은 알림중 읽지 않은 알림 개수 조회
	 * @param memberNo
	 * @return
	 */
	int notReadCheck(int memberNo);

	/** 알림창 X버튼 클릭시 삭제버튼
	 * @param notificationNo
	 */
	void deleteNotification(int notificationNo);

	/** 알림 읽음기능
	 * @param notificationNo
	 */
	void readNotification(int notificationNo);

}
