package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Equipment;
import entity.Product;
import service.ProductService;
import utils.FileUtils2;

public class ProductServiceimpl implements ProductService {

	@Override
	public boolean addProduct(Product pro) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}	
		//write data to file
		list.add(pro);
		fUtils.writeData(list, "products.data");	
		new ProductTypeServiceimpl().setIsQuote(pro.getType(), "true");
		return true;
	}

	@Override
	public boolean deleteProduct(String id) throws IOException {
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
		for(Product pro : list) {
			if(pro.getIsAvailable().equals("true")) {
				if(pro.getId().equals(id)) {
					pro.setIsAvailable("false");
					fUtils.writeData(list, "products.data");	
					new ProductTypeServiceimpl().setIsQuote(pro.getType(), "false");
		 			return true;
				}
			}
		}
	}
		return false;
	}
	@Override
	public boolean changeProduct(Product pro) throws IOException {
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		
		//update data
		
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(Product pros : list) {
				if(pros.getId().equals(pro.getId())) {
					if(pros.getIsAvailable().equals("true")) {	
						new ProductTypeServiceimpl().setIsQuote(pros.getType(), "false");
						new ProductTypeServiceimpl().setIsQuote(pro.getType(), "true");
						pros.setDescription(pro.getDescription());
						pros.setName(pro.getName());
						pros.setSpecifications(pro.getSpecifications());
						pros.setType(pro.getType());
						fUtils.writeData(list, "products.data");	
						return true;
					}
					
				}
			}	
		}			
		return false;
	}

	@Override
	public Product searchProduct(String id) throws IOException {
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else{
			for(Product pro : list) {
			if(pro.getIsAvailable().equals("true")) {
				if(pro.getId().equals(id)) {
					return pro;
				}
			}
		}
		}
	return null;
	}

	@Override
	public List<Product> showProduct() throws IOException {
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
		}
		return list;
	}
	
	public boolean changeProductType(String oldtype,String newtype) {
		FileUtils2<List<Product>> fUtils = new FileUtils2<>();
		List<Product> list = fUtils.getData("products.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else{
			for(Product pro : list) {
			if(pro.getIsAvailable().equals("true")) {
				if(pro.getType().equals(oldtype)) {
					pro.setType(newtype);

				}
			}
		}
			fUtils.writeData(list, "products.data");	
			return true;
		}
		return false;
	}
}
