package com.metelsos.common.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 
* <pre>
* com.metelsos.common.util
*   |_ MetelSOSUtil.java
* </pre>
* 
* Desc : ���� ��ƿ Ŭ����
* @Author  : "Kim Kibeom"
* @Date    : 2016. 11. 14. ���� 3:05:39
* @Version :
 */
public class MetelSOSUtil {
	
	private Log log = LogFactory.getLog(MetelSOSUtil.class);
	
	//������ ����� ���
	private static final String noticeFilePath = "C:\\dev\\file\\notice\\";
	
	/**
	 * 
	 * Desc : ��й�ȣ �н� �� �ӽ� ��й�ȣ �����ϴ� �޼���
	 * @Method Name : generateTempPasswd
	 * @param type - �ӽ� ��й�ȣ Ÿ��
	 * @param cnt - �ӽ� ��й�ȣ ������
	 * @return �ӽ� ��й�ȣ
	 */
	public String generateTempPasswd(String type, int cnt){
		StringBuffer strPwd = new StringBuffer();
		char str[] = new char[1];
		
		// Ư����ȣ ����
		if (type.equals("P")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 94) + 33);
				strPwd.append(str);
			}
		// �빮�ڷθ�
		} else if (type.equals("A")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 65);
				strPwd.append(str);
			}
		// �ҹ��ڷθ�
		} else if (type.equals("S")) {
			for (int i = 0; i < cnt; i++) {
				str[0] = (char) ((Math.random() * 26) + 97);
				strPwd.append(str);
			}
		// ����������
		} else if (type.equals("I")) {
			int strs[] = new int[1];
			for (int i = 0; i < cnt; i++) {
				strs[0] = (int) (Math.random() * 9);
				strPwd.append(strs[0]);
			}
		// �ҹ���, ������
		} else if (type.equals("C")) {
			Random rnd = new Random();
			for (int i = 0; i < cnt; i++) {
				if (rnd.nextBoolean()) {
					strPwd.append((char) ((int) (rnd.nextInt(26)) + 97));
				} else {
					strPwd.append((rnd.nextInt(10)));
				}
			}
		}
		
		return strPwd.toString();
	}
	
	/**
	 * 
	 * Desc : ���� ���� �޼���
	 * @Method Name : sendEmail
	 * @param receiver - ������ ���� �ּ�
	 * @param title - ���� ����
	 * @param content - ���� ����
	 */
	public void sendEmail(String receiver, String title, String content) {
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true");     // gmail�� ������ true ����
        p.put("mail.smtp.host", "smtp.gmail.com");      // smtp ���� �ּ�
        p.put("mail.smtp.auth","true");                 // gmail�� ������ true ����
        p.put("mail.smtp.port", "587");                 // gmail ��Ʈ
        
        Authenticator auth = new MyAuthentication();
        
        //session ���� ��  MimeMessage����
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
         
        try{
            //���������ð�
            msg.setSentDate(new Date());
             
            InternetAddress from = new InternetAddress() ;
             
             
            from = new InternetAddress("admin<mymetelsos@gmail.com>");
             
            // �̸��� �߽���
            msg.setFrom(from);
             
             
            // �̸��� ������
            InternetAddress to = new InternetAddress(receiver);
            msg.setRecipient(Message.RecipientType.TO, to);
             
            // �̸��� ����
            msg.setSubject(title, "UTF-8");
             
            // �̸��� ���� 
            msg.setText(content, "UTF-8");
             
            // �̸��� ��� 
            msg.setHeader("content-Type", "text/html");
             
            //���Ϻ�����
            javax.mail.Transport.send(msg);
             
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }
	}
	
	/**
	 * ���� �ð� String���� ����
	 * @param pattern - date ���� 
	 * @return
	 */
	public String currDatetoString(String pattern){
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat(pattern);

		return transFormat.format(date);
	}
	
	/**
	 * String���� �Ǿ��ִ� Ư�� �ð��� ���� �����ؼ� ����
	 * @param objectDate - ��� String
	 * @param beforePattern - ������ date ����
	 * @param afterPattern - ������ date ����
	 * @return
	 */
	public String changeDatePattern(String objectDate, String beforePattern, String afterPattern){
		SimpleDateFormat transFormat = new SimpleDateFormat(beforePattern);
		Date date = null;
		
		try {
			date = transFormat.parse(objectDate);
		} catch (ParseException e) {
			log.error(e,e);
		}
		transFormat.applyPattern(afterPattern);
		
		return transFormat.format(date);
	}
	
	/**
	 * 32������ ������ ���ڿ�(��������)�� ���� ��ȯ
	 * @return
	 */
	public String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	/**
	 * ������ ���� ��ο� �����ϰ� ���� ������ Map�� ��Ƽ� Map List�� ��ȯ��
	 * @param map - �Ķ���͸� ����ִ� Map ��ü
	 * @param request - ���� �����͸� ����ִ� HttpServletRequest ��ü
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,Object>> parseInsertFileInfo(HashMap<String,Object> paramMap, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> listMap = null; 
         
        String boardIdx = (String)paramMap.get("boardNum");
         
        File file = new File(noticeFilePath);
        if(file.exists() == false){
            file.mkdirs();
        }
         
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = getRandomString() + originalFileExtension;
                 
                file = new File(noticeFilePath + storedFileName);
                multipartFile.transferTo(file);
                 
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", boardIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                listMap.put("CREA_ID", paramMap.get("userName"));
                list.add(listMap);
            }
        }
        return list;
    }
}