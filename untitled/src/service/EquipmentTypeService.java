package service;

import java.io.IOException;
import java.util.List;

import entity.EquipmentType;


public interface EquipmentTypeService extends BaseService {
	public boolean addEquipmentType(EquipmentType equtype) throws IOException;
	public boolean deleteEquipmentType(String name) throws IOException;
	public boolean changeEquipmentType(EquipmentType equtype) throws IOException ;
	public EquipmentType searchEquipmentType(String name) throws IOException;
	public List<EquipmentType> showEquipmentType() throws IOException;
	public boolean setIsQuote(String name,String style)throws IOException;
}
