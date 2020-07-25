package controllers;

import java.io.IOException;
import java.util.List;

import entity.Schedule;
import factory.MyServiceFactory;
import service.ScheduleService;

public class ScheduleServiceController extends BaseController {
	private ScheduleService scheduleService;
	public ScheduleServiceController(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		scheduleService=(ScheduleService)MyServiceFactory.createService(message);
	}
	public boolean deleteEquipment(String id) throws IOException{
		return scheduleService.deleteEquipment(id);
	}
	public boolean addEquipment(Schedule  sc) throws IOException{
		return scheduleService.addEquipment(sc);
	}
	public List<Schedule> showSchedule() throws IOException{
		return scheduleService.showSchedule();
	}

}
