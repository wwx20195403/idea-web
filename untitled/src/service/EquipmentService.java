package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Equipment;
import utils.FileUtils2;

public interface EquipmentService extends BaseService {
	public boolean addEquipment(Equipment equips) throws IOException;
	public boolean deleteEquipment(String id) throws IOException;
	public boolean changeEquipment(Equipment equips) throws IOException ;
	public Equipment searchEquipment(String id) throws IOException;
	public List<Equipment> showEquipment() throws IOException;
}
