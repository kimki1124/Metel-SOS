package com.metelsos.qna.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.qna.vo.QnaVo;
/**
 * 
* <pre>
* com.metelsos.qna.dao
*   |_ QnaDao.java
* </pre>
* 
* Desc : ��ȸ�� QnA ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 11:13:32
* @Version :
 */
@Repository("qnaDao")
public class QnaDao extends AbstractDAO{

	public List<QnaVo> getQnaBest5() throws Exception{
		return (List<QnaVo>)selectList("qna.getQnaBest5");
	}
	
}
