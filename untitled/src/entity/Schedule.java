package entity;

import java.io.Serializable;

public class Schedule implements Serializable {
	private String OrderID;
	private String eqipmentID;
	private String starttime;
	private String endtime;
	private String isavailable="true";
	
	
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getEqipmentID() {
		return eqipmentID;
	}
	public void setEqipmentID(String eqipmentID) {
		this.eqipmentID = eqipmentID;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getIsavailable() {
		return isavailable;
	}
	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
	}

	
	
	
}
