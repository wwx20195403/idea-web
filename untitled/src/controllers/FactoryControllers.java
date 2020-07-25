package controllers;

import java.io.IOException;
import java.util.List;

import entity.Factory;
import factory.MyServiceFactory;
import service.FactoryService;

public class FactoryControllers extends BaseController {
	private FactoryService factoryService;
	public FactoryControllers(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		factoryService=(FactoryService)MyServiceFactory.createService(message);
	}
	public List<Factory> showFactory() throws IOException{
	 	return factoryService.showFactory();
	}
	public boolean changeFactorystate(String userID,String choice) throws IOException{
		return factoryService.changeFactorystate(userID,choice);
	}
	public Factory sreachFactory(String userID) throws IOException{
		return factoryService.sreachFactory(userID);
	}

}
