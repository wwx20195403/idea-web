package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.EquipmentType;
import entity.ProductType;
import service.EquipmentTypeService;
import utils.FileUtils2;

public class EquipmentTypeServiceimpl implements EquipmentTypeService {
	@Override
	public boolean addEquipmentType(EquipmentType equtype) throws IOException {
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(EquipmentType equtypes : list) {
				if(equtypes.getIsAvailable().equals("true")) {
					if(equtypes.getName().equals(equtype.getName())) {
						return false;
					}
				} 
			}
		}
		list.add(equtype);
		fUtils.writeData(list, "equipmentType.data");	
		return true;
	}

	@Override
	public boolean deleteEquipmentType(String name) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(EquipmentType equtype : list) {
				if(equtype.getIsAvailable().equals("true")) {
					if(equtype.getName().equals(name)) {
						if(equtype.getIsQuote()==0) {
							equtype.setIsAvailable("false");
							fUtils.writeData(list, "equipmentType.data");	
							return true;
						}
					}
				}
			}
		}
		return false;
	
	}

	@Override
	public boolean changeEquipmentType(EquipmentType equtype) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			if(isMore(equtype.getName()))return false;
			for(EquipmentType equtypes : list) {
				if(equtypes.getIsAvailable().equals("true")) {
					if(equtypes.getSerialNumber().equals(equtype.getSerialNumber())) {
						if((new EquipmentServiceimpl()).changeEquipmentType(equtypes.getName(), equtype.getName())) {
							equtypes.setName(equtype.getName());
							fUtils.writeData(list, "equipmentType.data");	
							return true;
						}
					}
				}
			}
		
		}
		return false;
	}

	@Override
	public EquipmentType searchEquipmentType(String name) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(EquipmentType equtype : list) {
				if(equtype.getIsAvailable().equals("true")) {
					if(equtype.getName().equals(name)) {
							return equtype;
						}
					}
				}
			}
		return null;
		
	}

	@Override
	public List<EquipmentType> showEquipmentType() throws IOException {
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}
		return list;
	}
	
	private boolean isMore(String name) {
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(EquipmentType equtypes : list) {
				if(equtypes.getIsAvailable().equals("true")) {
					if(equtypes.getName().equals(name)){
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
		FileUtils2<List<EquipmentType>> fUtils = new FileUtils2<>();
		List<EquipmentType> list = fUtils.getData("equipmentType.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(EquipmentType equtypes : list) {
				if(equtypes.getIsAvailable().equals("true")) {
					if(equtypes.getName().equals(name)) {
						equtypes.setIsQuote(style);
							fUtils.writeData(list, "equipmentType.data");	
							return true;
						
					}
				}
			}
		}
		return false;
	}
	
}
