package com.metelsos.support.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.support.vo.FileVo;
import com.metelsos.support.vo.SupportVo;
/**
 * 
* <pre>
* com.metelsos.support.dao
*   |_ SupportDao.java
* </pre>
* 
* Desc : ���� ��û �� ���� ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 8:42:11
* @Version :
 */
@Repository("supportDao")
public class SupportDao extends AbstractDAO{

	/**
	 * 
	 * Desc : ����Ÿ�Կ� ���� �����Ͼ�� �ڱⰡ ������ ���� ��û ����Ʈ, ���� ������ ��û�� ����Ʈ ������
	 * @Method Name : getSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> getSupportHistory(HashMap<String, String> paramMap) throws Exception{
		if("engineer".equals(paramMap.get("userType"))){
			return (List<SupportVo>)selectList("support.getSupportListByEngineerName", paramMap);
		}else{
			return (List<SupportVo>)selectList("support.getSupportListByCustomerName", paramMap);
		}
	}

	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ���� ��û ���� SELECT
	 * @Method Name : selectSupportCompleteCount
	 * @param interval
	 * @return
	 * @throws Exception
	 */
	public int selectSupportCompleteCount(int interval) throws Exception{
		return (int)selectOne("support.getSupportCompleteCount", interval);
	}

	/**
	 * 
	 * Desc : ���� �Ϸ���� ���� ���� ��û ���� SELECT
	 * @Method Name : selectSupportNotCompleteCount
	 * @param interval
	 * @return
	 */
	public int selectSupportNotCompleteCount(int interval) {
		return (int)selectOne("support.getSupportNotCompleteCount", interval);
	}

	/**
	 * 
	 * Desc : �� ȸ�� ���̵� �̿��ؼ� ������ ��û�� ���� ��û ����Ʈ ������
	 * @Method Name : selectSupportListByCustomerId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> selectSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectSupportListByCustomerId", userId);
	}

	/**
	 * 
	 * Desc : �� ȸ�� ���̵� �̿��ؼ� ������ ��û�� ���� �Ϸ� ������ ���� ��û ����Ʈ ������
	 * @Method Name : selectCompleteSupportListByCustomerId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SupportVo> selectCompleteSupportListByCustomerId(String userId) throws Exception{
		return (List<SupportVo>)selectList("support.selectCompleteSupportListByCustomerId", userId);
	}

	/**
	 * 
	 * Desc : ���� ��û �� ������� �� ���� ���̺� INSERT
	 * @Method Name : insertSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)insert("support.insertSupportRequest", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��û �ۿ� ÷������ �ø� �� ÷������ ���� INSERT
	 * @Method Name : uploadSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int uploadSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)insert("support.uploadSupportFile", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��û �� �󼼺���
	 * @Method Name : selectDetailSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public SupportVo selectDetailSupport(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.selectDetailSupport", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��û �� �󼼺��� �Ҷ� �ش� ���� ÷������ ����Ʈ SELECT
	 * @Method Name : selectFileList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		return (List<FileVo>)selectList("support.selectFileList", paramMap);
	}

	/**
	 * 
	 * Desc : FILE_NUM���� ÷�������� ���� ������
	 * @Method Name : selectSupportFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectSupportFileInfo(HashMap<String, Object> paramMap) throws Exception{
		return (FileVo)selectOne("support.selectSupportFileInfo", paramMap);
	}

	/**
	 * 
	 * Desc : ÷�������� ���� DELETE
	 * @Method Name : deleteSupportFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteSupportFile(HashMap<String, Object> paramMap) throws Exception{
		return (int)delete("support.deleteSupportFile", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��û �� ���� ���� �� UPDATE
	 * @Method Name : updateSupportRequest
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportRequest(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportRequest", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��û �� ���� �� ���̺��� DELETE
	 * @Method Name : deleteSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("support.deleteSupportHistory", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��� ������ ���� ��û�� ����Ʈ ������
	 * @Method Name : getUnSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getUnSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getUnSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ��� ������ ���� ��û�� �����Ͼ ���� �� ���� �Ϸ�� ����
	 * @Method Name : updateSupportHistory
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportHistory(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportHistory", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �Ϸ�� ���� ��û ����Ʈ SELECT
	 * @Method Name : getAcceptSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getAcceptSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getAcceptSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �� ������ ���� ��û ����Ʈ SELECT
	 * @Method Name : getSupportingList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getSupportingList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getSupportingList", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ���� ��û ����Ʈ SELECT
	 * @Method Name : getCompleteSupportList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getCompleteSupportList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("support.getCompleteSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ���� �� �ش� ������ ���� ��û�� ������ ����Ʈ ������ (ex. ���� �� ������ ���� ����Ʈ)
	 * @Method Name : getReqCompanyList
	 * @param support_state
	 * @return
	 */
	public List<String> getReqCompanyList(String support_state) {
		return (List<String>)selectList("support.getReqCompanyList", support_state);
	}

	/**
	 * 
	 * Desc : ���� ��� ������ ���� ��û ����Ʈ SELECT
	 * @Method Name : getWaitSupportList
	 * @param paramMap
	 * @return
	 */
	public List<HashMap<String, Object>> getWaitSupportList(HashMap<String, String> paramMap) {
		return (List<HashMap<String, Object>>)selectList("support.getWaitSupportList", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �Ϸ� ������ ���� ��û ����Ʈ SELECT
	 * @Method Name : selectAcceptSupport
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAcceptSupport(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectAcceptSupport", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ���� UPDATE
	 * @Method Name : updateSupportState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportState", paramMap);
	}

	/**
	 * 
	 * Desc : ���� ���� ���� ��û ���� ������, �����Ͼ� ����, ����� ����, ����� ID ������
	 * @Method Name : selectSupportingReq
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSupportingReq(HashMap<String, String> paramMap) throws Exception{
		return (HashMap<String, Object>)selectOne("support.selectEngAcceptSupport", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �� ������ ���� ��û�� ���� ���� UPDATE
	 * @Method Name : updateSupportingState
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int updateSupportingState(HashMap<String, String> paramMap) throws Exception{
		return (int)update("support.updateSupportingState", paramMap);
	}

	/**
	 * 
	 * Desc : SUPPORT_NUM���� �ش� ���� ��û ���� SELECT
	 * @Method Name : getCompleteSupDetail
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public SupportVo getCompleteSupDetail(HashMap<String, String> paramMap) throws Exception{
		return (SupportVo)selectOne("support.getCompleteSupDetail", paramMap);
	}

}
