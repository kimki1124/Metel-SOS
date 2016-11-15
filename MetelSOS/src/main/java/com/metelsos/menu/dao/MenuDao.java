package com.metelsos.menu.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.common.util.MetelSOSUtil;
import com.metelsos.menu.vo.MenuVo;
/**
 * 
* <pre>
* com.metelsos.menu.dao
*   |_ MenuDao.java
* </pre>
* 
* Desc : LEFTMENU ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 9:18:33
* @Version :
 */
@Repository("menuDao")
public class MenuDao extends AbstractDAO{

	/**
	 * 
	 * Desc : �����Ͼ�ȸ�� LEFTMENU ����Ʈ ������
	 * @Method Name : getEngineerLeftMenuList
	 * @return
	 */
	public List<MenuVo> getEngineerLeftMenuList() {
		return (List<MenuVo>)selectList("engineerMenu.getEngineerMenuList");
	}

	/**
	 * 
	 * Desc : ��ȸ�� LEFTMENU ����Ʈ ������
	 * @Method Name : getCustomerLeftMenuList
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> getCustomerLeftMenuList() throws Exception{
		return (List<MenuVo>)selectList("customerMenu.getCustomerMenuList");
	}
	
}
