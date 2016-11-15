package com.metelsos.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.metelsos.company.dao.CompanyDao;
import com.metelsos.company.vo.CompanyVo;
/**
 * 
* <pre>
* com.metelsos.company.service
*   |_ CompanyServiceImpl.java
* </pre>
* 
* Desc : ���� ���� ���� ���� Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 4:53:55
* @Version :
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	
	@Resource(name="companyDao")
	private CompanyDao companyDao;

	/**
	 * DAO���� �Ѱ��� CompanyVo ����Ʈ�� �״�� ��Ʈ�ѷ��� ����
	 * Desc : 
	 * @Method Name : setItemForRegisterForm
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CompanyVo> setItemForRegisterForm() throws Exception {
		return companyDao.setItemForRegisterForm();
	}
	
	
}
