package com.metelsos.notice.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.metelsos.common.dao.AbstractDAO;
import com.metelsos.notice.vo.FileVo;
import com.metelsos.notice.vo.NoticeVo;
/**
 * 
* <pre>
* com.metelsos.notice.dao
*   |_ NoticeDao.java
* </pre>
* 
* Desc : �������� ���� DAO Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 15. ���� 10:30:52
* @Version :
 */
@Repository("noticeDao")
public class NoticeDao extends AbstractDAO{

	/**
	 * 
	 * Desc : �������� ����Ʈ ������
	 * @Method Name : getNoticeList
	 * @return
	 * @throws Exception
	 */
	public List<NoticeVo> getNoticeList() throws Exception{
		return (List<NoticeVo>) selectList("notice.getNoticeList");
	}

	/**
	 * 
	 * Desc : ���ο� ���� �� �÷��� �� DB�� INSERT
	 * @Method Name : insertNoticeBoard
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int insertNoticeBoard(HashMap<String, String> paramMap) throws Exception{
		return (int) insert("notice.insertNoticeBoard", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �� �Խ��� �� ÷������ ���� ���� INSERT
	 * @Method Name : uploadNoticeFile
	 * @param paramMap
	 * @throws Exception
	 */
	public void uploadNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		insert("notice.uploadNoticeFile", paramMap);
	}

	/**
	 * 
	 * Desc : �ش� ���� �ۿ� ÷�ε� ���ϸ���Ʈ SELECT
	 * @Method Name : getNoticeFileList
	 * @param boardNum
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> getNoticeFileList(int boardNum) throws Exception{
		return (List<FileVo>)selectList("notice.getNoticeFileList", boardNum);
	}

	/**
	 * 
	 * Desc : ���� �� �ϳ��� ������ SELECT
	 * @Method Name : selectDetailNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public NoticeVo selectDetailNotice(HashMap<String, String> paramMap) throws Exception{
		return (NoticeVo)selectOne("notice.getDetailNotice", paramMap);
	}

	/**
	 * 
	 * Desc : getNoticeFileList �Լ��� ���� ���(�� �� �ϳ� ���� ����) 
	 * @Method Name : selectFileList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<FileVo> selectFileList(HashMap<String, String> paramMap) throws Exception{
		paramMap.put("boardNum", paramMap.get("notice_num"));
		return (List<FileVo>)selectList("notice.getNoticeFileList", paramMap);
	}

	/**
	 * 
	 * Desc : FILE_NUM���� ���� �ϳ��� ������ SELECT
	 * @Method Name : selectNoticeFileInfo
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectNoticeFileInfo(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("file_num")));
		return (FileVo)selectOne("notice.getNoticeFileInfo", file_num);
	}

	/**
	 * 
	 * Desc : ÷������ ���� �� DB�� �ִ� �ش� ������ ���� DELETE
	 * @Method Name : deleteNoticeFile
	 * @param paramMap
	 * @throws Exception
	 */
	public void deleteNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		delete("notice.deleteNoticeFile", file_num);
	}

	/**
	 * 
	 * Desc : ���� �� ���� �� �ش� �� ���� ���� DELETE
	 * @Method Name : deleteNotice
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNotice(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (int)delete("notice.deleteNotice", boardNum);
	}

	/**
	 * 
	 * Desc : �ش� ���� �ۿ� ÷�� ������ �ִ� �� Ȯ��
	 * @Method Name : selectHasNoticeFile
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public FileVo selectHasNoticeFile(HashMap<String, Object> paramMap) throws Exception{
		int boardNum = Integer.parseInt(String.valueOf(paramMap.get("boardNum")));
		return (FileVo)selectOne("notice.selectHasNoticeFile", boardNum);
	}

	/**
	 * 
	 * Desc : FILE_NUM���� ÷�� ���� �ϳ��� ���õ� ���� DELETE
	 * @Method Name : deleteNoticeFileByFileNum
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public int deleteNoticeFileByFileNum(HashMap<String, Object> paramMap) throws Exception{
		int file_num = Integer.parseInt(String.valueOf(paramMap.get("file_num")));
		return (int)delete("notice.deleteNoticeFileByFileNum", file_num);
	}

	/**
	 * 
	 * Desc : ���� �� ���� �� �ش� �ۿ� ���õ� ���� UPDATE
	 * @Method Name : updateNotice
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateNotice(HashMap<String, String> paramMap) throws Exception{
		update("notice.updateNotice", paramMap);
	}

	/**
	 * 
	 * Desc : ���� �� ��ȸ �� �ش� �� ��ȸ �� ����
	 * @Method Name : updateNoticeHit
	 * @param paramMap
	 * @throws Exception
	 */
	public void updateNoticeHit(HashMap<String, String> paramMap) throws Exception{
		update("notice.updateNoticeHit", paramMap);
	}

	/**
	 * 
	 * Desc : ����¡ ó���� ���ؼ� ���������� START���� END������ ROWNUM�� �����͸� ������
	 * @Method Name : selectNoticeList
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectNoticeList(HashMap<String, String> paramMap) throws Exception{
		return (List<HashMap<String, Object>>)selectPagingList("notice.selectNoticeList", paramMap);
	}

}
