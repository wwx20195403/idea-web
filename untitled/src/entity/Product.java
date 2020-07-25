 package entity;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
public class Product implements Serializable{
	private String id;
	private String name;
	private String type;
	private String specifications;//规格
	private String description;//描述
	private String isAvailable="true";
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Product(){
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.id="PNO"+s.format(date);
	}
	
	public Product(String name, String type, String specifications, String description) {
		this();
		this.name = name;
		this.type = type;
		this.specifications = specifications;
		this.description = description;
	} 
	public String getId() {
		return id;
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

}
