package controllers;

import java.io.IOException;
import java.util.List;

import entity.ProductType;
import factory.MyServiceFactory;
import service.ProductService;
import service.ProductTypeService;

public class ProductTypeController extends BaseController {
	private ProductTypeService  productTypeService;
	public ProductTypeController(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		productTypeService=(ProductTypeService)MyServiceFactory.createService(message);
	}
	public boolean addProductType(ProductType protype) throws IOException{
	 	return productTypeService.addProductType(protype);
	}
	public boolean deleteProductType(String id) throws IOException{
		return productTypeService.deleteProductType(id);
	}
	public boolean changeProductType(ProductType protype) throws IOException{
		return productTypeService.changeProductType(protype);
	}
	public ProductType searchProductType(String name) throws IOException{
		return productTypeService.searchProductType(name);
	}
	public List<ProductType> showProductType() throws IOException{
		return productTypeService.showProductType();
	}
	public boolean setIsQuote(String name,String style)throws IOException{
		return productTypeService.setIsQuote(name, style);
	}
}
