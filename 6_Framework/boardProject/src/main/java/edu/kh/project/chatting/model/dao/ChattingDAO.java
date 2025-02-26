package edu.kh.project.chatting.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.dto.ChattingRoom;

@Repository
public class ChattingDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<ChattingRoom> selectChattingRoomList(int memberNo) {
		return sqlSession.selectList("chattingMapper.selectChattingRoomList",memberNo);
	}
}
