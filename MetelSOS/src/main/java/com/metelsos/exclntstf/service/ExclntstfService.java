package com.metelsos.exclntstf.service;

import java.util.HashMap;

import com.metelsos.exclntstf.vo.ExclntStfVo;
/**
 * 
* <pre>
* com.metelsos.exclntstf.service
*   |_ ExclntstfService.java
* </pre>
* 
* Desc : ������ ���� ���� �������̽�
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 6:27:46
* @Version :
 */
public interface ExclntstfService {

	HashMap<String, Object> deleteExclntStf(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> setEngineerNameList(HashMap<String, String> paramMap) throws Exception;

	HashMap<String, Object> enrollExclntStf(ExclntStfVo vo) throws Exception;

	HashMap<String, Object> getExclntStfImage(HashMap<String, String> paramMap) throws Exception;

}
