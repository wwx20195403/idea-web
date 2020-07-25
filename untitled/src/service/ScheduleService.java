package service;

import java.io.IOException;
import java.util.List;

import entity.Schedule;


public interface ScheduleService extends BaseService {
	public boolean deleteEquipment(String id) throws IOException;
	public boolean addEquipment(Schedule  sc) throws IOException;
	public List<Schedule> showSchedule() throws IOException;
}
