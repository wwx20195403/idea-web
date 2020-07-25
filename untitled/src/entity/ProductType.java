package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductType implements Serializable {
	private String serialNumber;
	private String name;
	private int isQuote;
	private String isAvailable="true";
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsQuote() {
		return isQuote;
	}
	public void setIsQuote(String a) {
		if(a.equals("true")) {
			isQuote++; 
		}else {
			isQuote--;
		}
	}
	public ProductType(String name) {
		this();
		this.name = name;
		this.isQuote = 0;
	}
	public ProductType() {
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.serialNumber=s.format(date);
	}
}
