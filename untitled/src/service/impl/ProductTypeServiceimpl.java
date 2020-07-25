package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.ProductType;
import service.ProductTypeService;
import utils.FileUtils2;

public class ProductTypeServiceimpl implements ProductTypeService {

	@Override
	public boolean addProductType(ProductType protype) throws IOException {
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getName().equals(protype.getName())) {
						return false;
					}
				}
			}
		}
		list.add(protype);
		fUtils.writeData(list, "productType.data");	
		return true;
	}

	@Override
	public boolean deleteProductType(String name) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getName().equals(name)) {
						if(protypes.getIsQuote()==0) {
							protypes.setIsAvailable("false");
							fUtils.writeData(list, "productType.data");	
							return true;
						}
					}
				}
			}
		}
		return false;
	
	}

	@Override
	public boolean changeProductType(ProductType protype) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			if(isMore(protype.getName()))return false;
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getSerialNumber().equals(protype.getSerialNumber())) {
						if((new ProductServiceimpl()).changeProductType(protypes.getName(), protype.getName())) {
							protypes.setName(protype.getName());
							fUtils.writeData(list, "productType.data");	
							return true;
						}
					}
				}
			}
		}
		return false;
		
	}

	@Override
	public ProductType searchProductType(String name) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getName().equals(name)) {
							return protypes;
						}
					}
				}
			}
		return null;
		
	}

	@Override
	public List<ProductType> showProductType() throws IOException {
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}
		return list;
	}

	private boolean isMore(String name) {
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getName().equals(name)) {
							return true;
						}
					}
				}
			}
		return false;
	}
	@Override
	public boolean setIsQuote(String name, String style) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<ProductType>> fUtils = new FileUtils2<>();
		List<ProductType> list = fUtils.getData("productType.data");
	
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(ProductType protypes : list) {
				if(protypes.getIsAvailable().equals("true")) {
					if(protypes.getName().equals(name)) {
								protypes.setIsQuote(style);
								fUtils.writeData(list, "productType.data");	
								return true;
								
						}
					}
				}
			}
		return false;
	}
	


}
