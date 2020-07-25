package factory;

import service.BaseService;
import service.impl.*;

public class MyServiceFactory {
	 public static BaseService createService(String message) {
			
	    	BaseService  baseService=null;
			 
	    	if ("EquipmentService".equals(message))
	    		baseService = new EquipmentServiceimpl();
	    	 	
	    	if ("UserService".equals(message))
	    		baseService = new UserServiceImpl();

	    	if ("EquipmentTypeService".equals(message))
	    		baseService = new EquipmentTypeServiceimpl();
	    	
	    	if ("ProductService".equals(message))
	    		baseService = new ProductServiceimpl();
	    	
	    	if ("ProductTypeService".equals(message))
	    		baseService = new ProductTypeServiceimpl();
	    	
	    	if ("FactoryService".equals(message))
	    		baseService = new FactoryServiceimpl();
	    	if ("OrderService".equals(message))
	    		baseService = new OrderServiceimpl();
	    	
	    	if ("ScheduleService".equals(message))
	    		baseService = new ScheduleServiceimpl();
	        return baseService;
		}
}
