package com.metelsos.meeting.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.meeting.vo.INRMeetingVo;
/**
 * 
* <pre>
* com.metelsos.meeting.dao
*   |_ INRMeetingDao.java
* </pre>
* 
* Desc : ���� ȸ�Ƿ� ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 9:16:41
* @Version :
 */
@Repository("inrmeetingDao")
public class INRMeetingDao extends AbstractDAO{

	public List<INRMeetingVo> getMeetingHistory(HashMap<String, String> paramMap) throws Exception{
		return (List<INRMeetingVo>)selectList("inrmeeting.getMeetingHistory", paramMap);
	}

}
