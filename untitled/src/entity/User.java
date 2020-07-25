package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private String name;
	private int phone;
	private String type;
	private String isAvailable="true";
	private String userNumber;
	private String FacName;
	private String Facdes;
	public String getUserNumber() {
		return userNumber;
	}
	public String getFacName() {
		return FacName;
	}
	public User(String id, String password, String name, int phone, String type, String facName, String facdes) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.type = type;
		FacName = facName;
		Facdes = facdes; 
	}
	public void setFacName(String facName) {
		FacName = facName;
	}
	public String getFacdes() {
		return Facdes;
	}
	public void setFacdes(String facdes) {
		Facdes = facdes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public  User() {
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.userNumber="Us"+s.format(date);
	}


}

