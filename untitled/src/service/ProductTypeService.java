package service;

import java.io.IOException;
import java.util.List;

import entity.ProductType;


public interface ProductTypeService extends BaseService {
	public boolean addProductType(ProductType protype) throws IOException;
	public boolean deleteProductType(String name) throws IOException;
	public boolean changeProductType(ProductType protype) throws IOException ;
	public ProductType searchProductType(String name) throws IOException;
	public List<ProductType> showProductType() throws IOException;
	public boolean setIsQuote(String name,String style)throws IOException;
}
