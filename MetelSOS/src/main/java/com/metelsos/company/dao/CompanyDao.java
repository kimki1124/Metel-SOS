package com.metelsos.company.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.company.vo.CompanyVo;

/**
 * 
* <pre>
* com.metelsos.company.dao
*   |_ CompanyDao.java
* </pre>
* 
* Desc : ���� ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 4:52:40
* @Version :
 */
@Repository("companyDao")
public class CompanyDao extends AbstractDAO{
	/**
	 * 
	 * Desc : ������ ����� �����ͼ� CompanyVo ����Ʈ ���·� ����
	 * @Method Name : setItemForRegisterForm
	 * @return
	 * @throws Exception
	 */
	public List<CompanyVo> setItemForRegisterForm() throws Exception{
		return (List<CompanyVo>)selectList("company.getCustomerCompanyName");
	}

}
