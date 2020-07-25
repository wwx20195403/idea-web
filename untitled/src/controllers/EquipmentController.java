package controllers;

import java.io.IOException;
import java.util.List;

import entity.Equipment;
import factory.MyServiceFactory;
import service.EquipmentService;
import service.impl.EquipmentServiceimpl;

public class EquipmentController extends BaseController {
	private EquipmentService  equipmentService=null;
	public EquipmentController(String message) {
		super(message);
	 	equipmentService=(EquipmentService)MyServiceFactory.createService(message);
	}
	public boolean addEquipment(Equipment equips) throws IOException{
		return equipmentService.addEquipment(equips);
	}
	public boolean deleteEquipment(String id) throws IOException{
		return equipmentService.deleteEquipment(id);
	}
	public boolean changeEquipment(Equipment equip) throws IOException{
		return equipmentService.changeEquipment(equip);
	}
	public Equipment searchEquipment(String id) throws IOException{
		return equipmentService.searchEquipment(id);
	}
	public List<Equipment> showEquipment() throws IOException{
		return equipmentService.showEquipment();
	}
}
