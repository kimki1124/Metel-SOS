package com.metelsos.company.service;

import java.util.List;

import com.metelsos.company.vo.CompanyVo;
/**
 * 
* <pre>
* com.metelsos.company.service
*   |_ CompanyService.java
* </pre>
* 
* Desc : ���� ���� ���� �������̽�
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 4:53:38
* @Version :
 */
public interface CompanyService {

	List<CompanyVo> setItemForRegisterForm() throws Exception;

}
