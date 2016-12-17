package com.metelsos.newemplyd.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.newemplyd.vo.NewemplydVo;
/**
 * 
* <pre>
* com.metelsos.newemplyd.dao
*   |_ NewemplydDao.java
* </pre>
* 
* Desc : ���Ի�� ���� 
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 9:47:14
* @Version :
 */
@Repository("newemplydDao")
public class NewemplydDao extends AbstractDAO{

	/**
	 * 
	 * Desc : CREATE_DATE�� ���� ��� ���� �����Ͼ��� ����Ʈ�� ������
	 * @Method Name : getCurrNewEmplydList
	 * @param year_month
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCurrNewEmplydList(String year_month) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getCurrNewEmplydList", year_month);
	}

	/**
	 * 
	 * Desc : �� �޼���� ��� ����(�� �� �ϳ� ���� ����)
	 * @Method Name : getNewEmplydList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getNewEmplydList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectList("newemplyd.getNewEmplydList", paramMap);
	}

	/**
	 * 
	 * Desc : ���Ի�� ��Ͽ��� ��� ���� DELETE
	 * @Method Name : deleteNewEmplyd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("newemplyd.deleteNewEmplyd", paramMap);
	}

	/**
	 * 
	 * Desc : ���ο� ���Ի���� ������ ���Ի�� ���̺� INSERT
	 * @Method Name : enrollNewEmplyd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int enrollNewEmplyd(HashMap<String, Object> paramMap) throws Exception{
		if(paramMap.get("engineer_image") != null){
			return (int)insert("newemplyd.enrollNewEmplyd", paramMap);
		}else{
			return (int)insert("newemplyd.enrollNewEmplydDefaultImg", paramMap);
		}
		
	}

	/**
	 * 
	 * Desc : ���Ի���� �̹��������� ������ ���̺��� SELECT
	 * @Method Name : getNewEmplydImage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>) selectOne("newemplyd.getNewEmplydImage", paramMap);
	}

}
