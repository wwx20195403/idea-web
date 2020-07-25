package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
	private String id;
	private String productname;
	private int productnumber; 
	private String deliverydate;//订单完成日期
	private String tenderdeadline;//订单投标截止日期
	private String ordernumber;
	private String  ordetstate;//订单状态
	private String  factoryID;
	private String  traderID;
	


	public String getId() {
		return id;
	}

	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getProductnumber() {
		return productnumber;
	}
	public void setProductnumber(int productnumber) {
		this.productnumber = productnumber;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getTenderdeadline() {
		return tenderdeadline;
	}
	public void setTenderdeadline(String tenderdeadline) {
		this.tenderdeadline = tenderdeadline;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public String getOrdetstate() {
		return ordetstate;
	}
	public void setOrdetstate(String ordetstate) {
		this.ordetstate = ordetstate;
	}
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getTraderID() {
		return traderID;
	}
	public void setTraderID(String traderID) {
		this.traderID = traderID;
	}
	
	public Order(){
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat( "yyyyMMddHHmmssSS");
		this.ordernumber=s.format(date);
		this.id="Ord"+s.format(date);
		this.ordetstate="已保存";
		this.factoryID="null";
	}
	
	
	

	
}
