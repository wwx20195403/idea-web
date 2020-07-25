package controllers;

import java.io.IOException;
import java.util.List;

import entity.EquipmentType;
import factory.MyServiceFactory;
import service.EquipmentTypeService;

public class EquipmentTypeController extends BaseController {
	private EquipmentTypeService equipmentTypeService;
	public EquipmentTypeController(String message) {
		super(message);
	 	// TODO Auto-generated constructor stub
		equipmentTypeService=(EquipmentTypeService)MyServiceFactory.createService(message);
	}
	public boolean addEquipmentType(EquipmentType equtype) throws IOException{
		return equipmentTypeService.addEquipmentType(equtype);
	}
	public boolean deleteEquipmentType(String name) throws IOException{
		return equipmentTypeService.deleteEquipmentType(name);
	}
	public boolean changeEquipmentType(EquipmentType equtype) throws IOException{
		return equipmentTypeService.changeEquipmentType(equtype);
	}
	public EquipmentType searchEquipmentType(String name) throws IOException{
		return equipmentTypeService.searchEquipmentType(name);
	}
	public List<EquipmentType> showEquipmentType() throws IOException{
		return equipmentTypeService.showEquipmentType();
	}
	public boolean setIsQuote(String name,String style)throws IOException{
		return equipmentTypeService.setIsQuote(name, style);
	}

}
