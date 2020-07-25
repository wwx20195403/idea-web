package service;

import java.io.IOException;
import java.util.List;

import entity.Product;


public interface ProductService extends BaseService {
	public boolean addProduct(Product pro) throws IOException;
	public boolean deleteProduct(String id) throws IOException;
	public boolean changeProduct(Product pro) throws IOException ;
	public Product searchProduct(String id) throws IOException;
	public List<Product> showProduct() throws IOException;	
}
