package service;

import java.io.IOException;
import java.util.List;

import entity.Order;

public interface OrderService extends BaseService {
	public boolean addOrder(Order order) throws IOException;
	public boolean changeOrder(Order ord) throws IOException ;
	public Order searchOrder(String id) throws IOException;
	public List<Order> showOrder() throws IOException;
	public boolean endOrder(String orderId)  throws IOException;
}
