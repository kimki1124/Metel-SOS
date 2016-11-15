package com.metelsos.customer.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.customer.vo.CustomerVo;

/**
 * 
* <pre>
* com.metelsos.customer.dao
*   |_ CustomerDao.java
* </pre>
* 
* Desc : ���� �Ҽ� �� ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 4:59:52
* @Version :
 */
@Repository("customerDao")
public class CustomerDao extends AbstractDAO{

	/**
	 * 
	 * Desc : ID�� ��й�ȣ�� SELECT�� CustomerVo �ϳ� ����
	 * @Method Name : checkLogin
	 * @param paramMap - ���������� ���� ID�� ��й�ȣ�� ����ִ� Map
	 * @return
	 * @throws Exception
	 */
	public CustomerVo checkLogin(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndPasswd", paramMap);
	}

	/**
	 * 
	 * Desc : ID�� SELECT�� CustomerVo �ϳ� ����
	 * @Method Name : validateCustomerId
	 * @param paramMap - ���������� ���� ID�� ����ִ� Map
	 * @return
	 * @throws Exception
	 */
	public CustomerVo validateCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerById", paramMap);
	}

	/**
	 * 
	 * Desc : ȸ������ �� ���� ������ INSERT
	 * @Method Name : insertUser
	 * @param paramMap - ȸ������ ������ ���� �ۼ��� �����Ͱ� ����ִ� Map
	 * @return
	 * @throws Exception
	 */
	public int insertUser(HashMap<String, Object> paramMap) throws Exception{
		return (int) insert("customer.insertCustomer", paramMap);
	}

	/**
	 * 
	 * Desc : ID�� �̸��Ϸ� �� ȸ���� ������ ã�� �޼���
	 * @Method Name : findCustomerId
	 * @param paramMap - ID�� �̸��� �����Ͱ� ����ִ� Map
	 * @return
	 * @throws Exception
	 */
	public List<CustomerVo> findCustomerId(HashMap<String, String> paramMap) throws Exception{
		return (List<CustomerVo>)selectList("customer.getCustomerByNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : ID, �̸�, �̸��Ϸ� ��ȸ�� ���� ã�� �޼��� 
	 * @Method Name : findCustomer
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public CustomerVo findCustomer(HashMap<String, String> paramMap) throws Exception{
		return (CustomerVo)selectOne("customer.getCustomerByIdAndNameAndEmail", paramMap);
	}

	/**
	 * 
	 * Desc : ��ȸ�� ��й�ȣ ���� 
	 * @Method Name : updateCustomerPasswd
	 * @param vo
	 * @throws Exception
	 */
	public void updateCustomerPasswd(CustomerVo vo) throws Exception{
		update("customer.updateCustomerPasswd", vo);
	}

	/**
	 * 
	 * Desc : ��ȸ������ ������Ʈ
	 * @Method Name : updateCustomerInfo
	 * @param vo
	 * @throws Exception
	 */
	public void updateCustomerInfo(CustomerVo vo) throws Exception{
		update("customer.updateCustomerInfo", vo);
	}

	/**
	 * 
	 * Desc : ��ȸ��Ż��
	 * @Method Name : deleteCustomerAccount
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomerAccount(HashMap<String, String> paramMap) throws Exception{
		return (int)delete("customer.deleteCustomer", paramMap);
	}

	/**
	 * 
	 * Desc : ID�� ��ȸ�� ���� SELECT
	 * @Method Name : getCustomerInfo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getCustomerInfo(String userId) throws Exception{
		return (HashMap<String, Object>)selectOne("customer.getCustomerInfo", userId);
	}

	/**
	 * 
	 * Desc : CUSTOMER_NAME�ϰ� CUSTOMER_PHONE���� ��ȸ������ SELECT
	 * @Method Name : selectCustomerPosition
	 * @param supportMap
	 * @return
	 * @throws Exception
	 */
	public String selectCustomerPosition(HashMap<String, String> supportMap) throws Exception{
		return (String)selectOne("customer.selectCustomerPosition", supportMap);
	}

}
