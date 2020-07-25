package controllers;

import java.io.IOException;
import java.util.List;

import entity.User;
import factory.MyServiceFactory;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserController extends BaseController {
	private UserService  userService;
	public UserController(String message) {
		super(message);
		userService=(UserService)MyServiceFactory.createService(message);
	}
	public boolean validate(String id,String password) {
 		return userService.validate(id, password);
	}
	public boolean deleteUser(String id) throws IOException{
		return userService.deleteUser(id);
	}
	public boolean addUser(User user) throws IOException{
		return userService.addUser(user);
	}
	public User searchUser(String id) throws IOException{
		return userService.searchUser(id);
	} 
	public boolean changeUser(User user) throws IOException{
		return userService.changeUser(user);
	}
	public List<User> showUser() throws IOException{
		return userService.showUser();
	}
	
}
