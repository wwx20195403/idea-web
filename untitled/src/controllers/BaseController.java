package controllers;

import factory.MyServiceFactory;
import service.BaseService;

public class BaseController {

	private BaseService baseService;
	
	public BaseController(String message)
	{
		baseService = MyServiceFactory.createService(message);
	} 
}
