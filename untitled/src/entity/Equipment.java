package entity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Equipment implements Serializable  {
//	public static void main(String[] args) {
//		Equipment e1=new Equipment();
//		Equipment e2=new Equipment("", "", "", "", "", "");
//		System.out.println(e1.getId());
//		System.out.println(e2.getId());
//	} 
	private String id;
	private String name;
	private String type;
	private String specifications;//规格
	private String description;//描述
	private String equiomentState;//闲置还是启动
	private String isRent;//来源,租借还是自有
	private String belong;
	private String nowBelong;
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getNowBelong() {
		return nowBelong;
	}
	public void setNowBelong(String nowBelong) {
		this.nowBelong = nowBelong;
	}
	private String isAvailable="true";
	
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEquiomentState() {
		return equiomentState;
	}
	public void setEquiomentState(String equiomentState) {
		this.equiomentState = equiomentState;
	}
	public String getIsRent() {
		return isRent;
	}

	public void setIsRent(String isRent) {
		this.isRent = isRent;
	}
	public String getId() {
		return id;
	}
	public Equipment() {
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.id="DNO"+s.format(date);
		equiomentState="闲置中";
	};
	
	
	
	

}
