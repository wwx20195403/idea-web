package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Factory;
import entity.User;
import service.FactoryService;
import utils.FileUtils2;

public class FactoryServiceimpl implements FactoryService {

	@Override
	public List<Factory> showFactory() throws IOException {
		// TODO Auto-generated method stub
	FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
	List<Factory> list = fUtils.getData("factory.data");
	if(list == null)
	{
		list = new ArrayList<>();
	}
	return list;
	}

	@Override
	public boolean changeFactorystate(String userID,String choice) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else {
			for(Factory fac:list) {
				if(fac.getIsAvailable().equals("true")) {
					if(fac.getUserId().equals(userID)) {
						fac.setFctorystate(choice);
						fUtils.writeData(list, "factory.data");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	public void createFactory(User user) {
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}
		list.add(new Factory(user.getFacName(), user.getFacdes(), user.getName(), user.getId(),user.getUserNumber() ));
		fUtils.writeData(list, "factory.data");
	}
	
	
	public boolean deleteFactory(String userNumber){
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else {
			for(Factory fac:list) {
				if(fac.getIsAvailable().equals("true")) {
					if(fac.getUserNumber().equals(userNumber)) {
						fac.setIsAvailable("false");
						fUtils.writeData(list, "factory.data");	
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean changeFactory(User use){
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else {
			for(Factory fac:list) {
				if(fac.getIsAvailable().equals("true")) {
					if(fac.getUserNumber().equals(use.getUserNumber())) {
						fac.setIntroduction(use.getFacdes());
						fac.setName(use.getFacName());
						fac.setUsername(use.getName());
						fUtils.writeData(list, "factory.data");
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Factory sreachFactory(String userID) throws IOException {
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else {
			for(Factory fac:list) {
				if(fac.getIsAvailable().equals("true")) {
					if(fac.getUserId().equals(userID)) {
						return fac;
					}
				}
			}
		}
		return null;
	}
	
	public String getFactoryState(String userID) throws IOException {
		FileUtils2<List<Factory>> fUtils = new FileUtils2<>();
		List<Factory> list = fUtils.getData("factory.data");
		if(list == null)
		{
			list = new ArrayList<>();
		}else {
			for(Factory fac:list) {
				if(fac.getIsAvailable().equals("true")) {
					if(fac.getUserId().equals(userID)) {
						return fac.getFctorystate();
					}
				}
			}
		}
		return null;
	}
	
	
	
}
