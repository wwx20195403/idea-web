package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Schedule;
import service.ScheduleService;
import utils.FileUtils2;

public class ScheduleServiceimpl implements ScheduleService {

	@Override
	public boolean deleteEquipment(String id) throws IOException {
		FileUtils2<List<Schedule>> fUtils = new FileUtils2<>();
		List<Schedule> list = fUtils.getData("schedule.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
			for(Schedule s:list) {
				if(s.getIsavailable().equals("true")) {
					if(s.getEqipmentID().equals(id)) {
						s.setIsavailable("false");
						fUtils.writeData(list, "schedule.data");
						new EquipmentServiceimpl().EquipmentState_torest(s.getEqipmentID());
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean addEquipment(Schedule  sc) throws IOException {
		
		FileUtils2<List<Schedule>> fUtils = new FileUtils2<>();
		List<Schedule> list = fUtils.getData("schedule.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}
		list.add(sc);
		new OrderServiceimpl().orderSchedule(sc.getOrderID());
		new EquipmentServiceimpl().EquipmentState_towork(sc.getEqipmentID());
		fUtils.writeData(list, "schedule.data");	
		return true;
	}

	@Override
	public List<Schedule> showSchedule() throws IOException {
		FileUtils2<List<Schedule>> fUtils = new FileUtils2<>();
		List<Schedule> list = fUtils.getData("schedule.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}
		
		return list;
	}
	
	
	public boolean endOrder(String orderId)throws IOException {
		FileUtils2<List<Schedule>> fUtils = new FileUtils2<>();
		List<Schedule> list = fUtils.getData("schedule.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
			for(Schedule sc:list) {
				if(sc.getIsavailable().equals("true")) {
					if(sc.getOrderID().equals(orderId)) {
						deleteEquipment(sc.getEqipmentID());
					}
				}
			}
			return true;
		}
		return false;
	}

}
