package com.metelsos.engineer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.engineer.vo.EngineerVo;
/**
 * 
* <pre>
* com.metelsos.engineer.dao
*   |_ EngineerDao.java
* </pre>
* 
* Desc : �����Ͼ� ȸ������ DAOŬ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 6:02:46
* @Version :
 */
@Repository("engineerDao")
public class EngineerDao extends AbstractDAO{

	/**
	 * 
	 * Desc : ID�� ��й�ȣ�� �����Ͼ�ȸ�� SELECT
	 * @Method Name : checkLogin
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByIdAndPasswd", paramMap);
	}

	/**
	 * 
	 * Desc : ID�� �����Ͼ� SELECT
	 * @Method Name : validateEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo validateEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerById", paramMap);
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ������ �� ȸ������ ���� �ۼ��� ȸ������ INSERT
	 * @Method Name : insertUser
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertUser(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("engineer.insertEngineer", paramMap);
	}

	/**
	 * 
	 * Desc : �̸��� �̸��Ϸ� �����Ͼ� ȸ�� ���� SELECT
	 * @Method Name : findEngineerId
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<EngineerVo> findEngineerId(HashMap<String, String> paramMap) throws Exception{
		return (List<EngineerVo>)selectList("engineer.getEngineerByNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : ID�� �����Ͼ� ȸ�� ���� SELECT
	 * @Method Name : findEngineer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public EngineerVo findEngineer(HashMap<String, String> paramMap) throws Exception{
		return (EngineerVo)selectOne("engineer.getEngineerByUserId", paramMap);
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ�� ��й�ȣ UPDATE
	 * @Method Name : updateEngineerPasswd
	 * @param vo
	 */
	public void updateEngineerPasswd(EngineerVo vo) {
		update("engineer.updateEngineerPasswd", vo);
	}

	/**
	 * 
	 * Desc : �����Ͼ� ���������� ���� �� ȸ������ UPDATE
	 * @Method Name : updateEngineerInfo
	 * @param vo
	 * @throws Exception
	 */
	public void updateEngineerInfo(EngineerVo vo) throws Exception{
		update("engineer.updateEngineerInfo", vo);
	}

	/**
	 * 
	 * Desc : �����Ͼ� ȸ�� Ż�� �� �ش� �����Ͼ� ���� DELETE
	 * @Method Name : deleteEngineerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteEngineerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("engineer.deleteEngineer", paramMap);
	}

	/**
	 * 
	 * Desc : �����Ͼ �Ҽӵ� �μ��� ����Ʈ SELECT
	 * @Method Name : getDeptList
	 * @return
	 * @throws Exception
	 */
	public List<String> getDeptList() throws Exception{
		return (List<String>)selectList("engineer.getDeptList");
	}

	/**
	 * 
	 * Desc : �ش� �μ��� �Ҽӵ� �����Ͼ���� ����Ʈ SELECT
	 * @Method Name : getEngineerNameListByDept
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<String> getEngineerNameListByDept(HashMap<String, String> paramMap) throws Exception{
		return (List<String>)selectList("engineer.getEngineerNameListByDept", paramMap);
	}

	/**
	 * 
	 * Desc : �ش� �����Ͼ��� ENGINEER_POSITION SELECT
	 * @Method Name : getEngineerPosition
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String getEngineerPosition(HashMap<String, Object> paramMap) throws Exception{
		return (String)selectOne("engineer.getEngineerPosition", paramMap);
	}

	/**
	 * 
	 * Desc : �ش� ���� ���� �����Ͼ���� ENGINEER_NAME ����Ʈ SELECT
	 * @Method Name : getNewEmplydNameList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<String> getNewEmplydNameList(HashMap<String, String> paramMap) throws Exception{
		return (List<String>)selectList("engineer.getNewEmplydNameList", paramMap);
	}

	/**
	 * 
	 * Desc : �ش� �����Ͼ��� ENGINEER_POSITION SELECT
	 * @Method Name : selectEngineerPosition
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String selectEngineerPosition(HashMap<String, String> paramMap) throws Exception{
		return (String)selectOne("engineer.selectEngineerPosition", paramMap);
	}

}
