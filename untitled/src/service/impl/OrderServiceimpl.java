package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.Order;
import entity.Product;
import service.OrderService;
import utils.FileUtils2;

public class OrderServiceimpl implements OrderService {

	@Override
	public boolean addOrder(Order order) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}	

		list.add(order);
		fUtils.writeData(list, "order.data");	
		return true;	
	}

	@Override
	public boolean changeOrder(Order ord) throws IOException {
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		for(Order order : list) {
			if(order.getId().equals(ord.getId())) {
				order.setFactoryID(ord.getFactoryID());
				order.setOrdetstate(ord.getOrdetstate());
				order.setDeliverydate(ord.getDeliverydate());
				order.setProductname(ord.getProductname());
				order.setTenderdeadline(ord.getTenderdeadline());
				order.setProductnumber(ord.getProductnumber());
				fUtils.writeData(list, "order.data");
				return true;
			}
			
		}
		return false;	
	}

	@Override
	public Order searchOrder(String id) throws IOException {
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		for(Order order : list) {
			if(order.getId().equals(id)) {
				return order;
			}
			
		}
		return null;
	}

	@Override
	public List<Order> showOrder() throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}
		return list;
	}
	
	public boolean orderSchedule(String OrderId) throws IOException{
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(Order ord : list ) {
				if(ord.getId().equals(OrderId)) {
					if(ord.getOrdetstate().equals("已排产")) {
						return true;
					}else {
						ord.setOrdetstate("已排产");
						fUtils.writeData(list, "order.data");
						JOptionPane.showMessageDialog(null, "订单号"+ord.getId()+"\n进入排产");
						return true;
						
					}
				}
			}
		}
		return false;
	}
	
	public boolean endOrder(String orderId)  throws IOException{
		FileUtils2<List<Order>> fUtils = new FileUtils2<>();
		List<Order> list = fUtils.getData("order.data");
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(Order ord : list ) {
				if(ord.getId().equals(orderId)) {
					if(ord.getOrdetstate().equals("已排产")) {
						ord.setOrdetstate("已完工");
						JOptionPane.showMessageDialog(null, "订单号"+ord.getId()+"\n已完工");
						fUtils.writeData(list, "order.data");
						new ScheduleServiceimpl().endOrder(orderId);
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "不可进行此操作");
						
					}
				}
			}
		}
		return false;
	}
	


}
