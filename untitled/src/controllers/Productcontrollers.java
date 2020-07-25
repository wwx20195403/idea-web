package controllers;

import java.io.IOException;
import java.util.List;

import entity.Product;
import factory.MyServiceFactory;
import service.ProductService;

public class Productcontrollers extends BaseController {
	private ProductService productService ;
	public Productcontrollers(String message) {
		super(message);
		productService=(ProductService)MyServiceFactory.createService(message);
	}
	public boolean addProduct(Product pro) throws IOException{
	 	return productService.addProduct(pro);
	}
	public boolean deleteProduct(String id) throws IOException{
		return productService.deleteProduct(id);
	}
	public boolean changeProduct(Product pro) throws IOException{
		return productService.changeProduct(pro);
	}
	public Product searchProduct(String id) throws IOException{
		return productService.searchProduct(id);
	}
	public List<Product> showProduct() throws IOException{
		return productService.showProduct();
	}
	

}
