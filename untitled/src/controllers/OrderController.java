package controllers;

import java.io.IOException;
import java.util.List;

import entity.Order;
import factory.MyServiceFactory;
import service.OrderService;

public class OrderController extends BaseController {
	private OrderService orderService;
	public OrderController(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		orderService=(OrderService)MyServiceFactory.createService(message);
	}
	public boolean addOrder(Order order) throws IOException{
		return orderService.addOrder(order);
	}
	public boolean changeOrder(Order ord) throws IOException{
		return orderService.changeOrder(ord);
	}
	public Order searchOrder(String id) throws IOException{
		return orderService.searchOrder(id);
	}
	public List<Order> showOrder() throws IOException{
		return orderService.showOrder();
	}
	public boolean endOrder(String orderId)  throws IOException{
		return orderService.endOrder(orderId);
	}
}
