package com.metelsos.newemplyd.service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.engineer.dao.EngineerDao;
import com.metelsos.newemplyd.dao.NewemplydDao;
import com.metelsos.newemplyd.vo.NewemplydVo;
/**
 * 
* <pre>
* com.metelsos.newemplyd.service
*   |_ NewemplydServiceImpl.java
* </pre>
* 
* Desc : ���Ի�� ���� ���� ���� Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 9:50:52
* @Version :
 */
@Service("newemplydService")
public class NewemplydServiceImpl implements NewemplydService{
	
	@Resource(name="newemplydDao")
	private NewemplydDao newemplydDao;
	
	@Resource(name="engineerDao")
	private EngineerDao engineerDao;

	/**
	 * 
	 * Desc : ���Ի�� ���� ���� 
	 * @Method Name : deleteNewEmplyd
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> deleteNewEmplyd(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", URLDecoder.decode(paramMap.get("engineer_dept"), "UTF-8"));
		paramMap.put("engineer_name", URLDecoder.decode(paramMap.get("engineer_name"), "UTF-8"));
		
		int result = newemplydDao.deleteNewEmplyd(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
			returnMap.put("engineer_dept", paramMap.get("engineer_dept"));
			returnMap.put("engineer_name", paramMap.get("engineer_name"));
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : ���Ի���� �̸� ����Ʈ�� ������
	 * @Method Name : setNewEmplydNameList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> setNewEmplydNameList(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", URLDecoder.decode(paramMap.get("engineer_dept"), "UTF-8"));
		
		if(paramMap.get("selectMonth").length() < 2){
			paramMap.put("selectMonth", "0"+paramMap.get("selectMonth"));
		}
		
		paramMap.put("year_month", paramMap.get("currYear")+paramMap.get("selectMonth"));
		
		List<String> list = engineerDao.getNewEmplydNameList(paramMap);
		
		returnMap.put("engineerNameList", list);
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : ���Ի���� ������ �߰�
	 * @Method Name : enrollNewEmplyd
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> enrollNewEmplyd(NewemplydVo vo) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		paramMap.put("new_emply_year_month", vo.getNew_emply_year_month());
		paramMap.put("engineer_dept", vo.getEngineer_dept());
		paramMap.put("engineer_name", vo.getEngineer_name());
		paramMap.put("impression_speech", vo.getImpression_speech());
		
		if(vo.getEngineer_image() != null){
			paramMap.put("engineer_image", vo.getEngineer_image().getBytes());
		}else{
			paramMap.put("engineer_image", null);
		}
		
		String engineerPosition = engineerDao.getEngineerPosition(paramMap);
		paramMap.put("engineer_position", engineerPosition);
		
		int result = newemplydDao.enrollNewEmplyd(paramMap);
		
		if(result > 0){
			returnMap.put("resultMsg", "SUCCESS");
		}else{
			returnMap.put("resultMsg", "FAILED");
		}
		
		return returnMap;
	}

	/**
	 * 
	 * Desc : ���Ի�� ���̺��� ���Ի�� �̹��� ������
	 * @Method Name : getNewEmplydImage
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<String, Object> getNewEmplydImage(HashMap<String, String> paramMap) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		paramMap.put("engineer_dept", new String(paramMap.get("engineer_dept").getBytes("iso-8859-1"), "utf-8"));
		paramMap.put("engineer_name", new String(paramMap.get("engineer_name").getBytes("iso-8859-1"), "utf-8"));
		returnMap = newemplydDao.getNewEmplydImage(paramMap);
		
		return returnMap;
	}
}
