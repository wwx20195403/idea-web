package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Factory implements Serializable {
	private String name;
	private String introduction;
	private String username;
	private String userId;
	private String fctorystate="正常";
	private String UserNumber;
	private String isAvailable="true";
	private String faNumber;
//	private String 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	} 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFctorystate() {
		return fctorystate;
	}
	public void setFctorystate(String fctorystate) {
		if(fctorystate.equals("true")) {
			this.fctorystate="正常";
		}else {
			this.fctorystate="关停";
		}
	}
	public String getUserNumber() {
		return UserNumber;
	}
	public void setUserNumber(String userNumber) {
		UserNumber = userNumber;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	public Factory(String name, String introduction, String username, String userId,String userNumber) {
		this();
		this.name = name;
		this.introduction = introduction;
		this.username = username;
		this.userId = userId;
		UserNumber = userNumber;
	}
	
	public Factory() {
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.faNumber="Fac"+s.format(date);
	}
	
}
