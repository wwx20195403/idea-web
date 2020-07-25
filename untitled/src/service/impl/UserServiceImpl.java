package service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtils2;

import entity.User;
import service.UserService;



public class UserServiceImpl implements UserService {
	@Override
	public boolean validate(String id, String password) {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
		for(User user : list) {
			if(user.getIsAvailable().equals("true")) {
				if(user.getId().equals(id)) {
					if(user.getPassword().equals(password)) {
						try {
							if(user.getType().equals("云工厂管理员")) {
								if((new FactoryServiceimpl()).getFactoryState(id).equals("正常")) {
									return true;
								}
							}else {
								return true;
							}
						
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		}
		return false;
	}
	@Override
	public boolean addUser(User user) throws IOException {
		// TODO Auto-generated method stub
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
 
		}else {
			for(User use : list) {
				if(user.getId().equals(use.getId())) {
					if(use.getIsAvailable().equals("true")) {
						return false;
					}
					
				}
			}
		}		
		//write data to file
		list.add(user);
		if(user.getFacName()!=" "){new FactoryServiceimpl().createFactory(user);}
		fUtils.writeData(list, "users.data");	
		return true;

}
	@Override
	public boolean deleteUser(String id) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
		for(User use : list) {
			if(use.getIsAvailable().equals("true")) {
				if(use.getId().equals(id)) {
					if(	new FactoryServiceimpl().deleteFactory(use.getUserNumber())) {
						new EquipmentServiceimpl().delete(use.getId());
						use.setIsAvailable("false");						
						fUtils.writeData(list, "users.data");	
						return true;
					}
				
				}
			}
		}
		}
	return false;
	}

	@Override
	public User searchUser(String id) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		if(list == null)
		{
			list = new ArrayList<>();

		}else{
			for(User use : list) {
			if(use.getIsAvailable().equals("true")) {
				if(use.getId().equals(id)) {
					return use;
				}
			}
		}
		}
	return null;
	}

	@Override
	public boolean changeUser(User user) throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();

		}else {
			for(User use : list) {
				if(use.getIsAvailable().equals("true")) {
					if(user.getId().equals(use.getId())) {
						if(user.getType().equals("经销商")) {
							use.setName(user.getName());
							use.setPassword(user.getPassword());
							use.setPhone(user.getPhone());
							use.setType(use.getType());
							use.setFacdes(user.getFacdes());
							fUtils.writeData(list, "users.data");
											
							return true;
						}else {
							if(new FactoryServiceimpl().changeFactory(user)) {
								use.setName(user.getName());
								use.setPassword(user.getPassword());
								use.setPhone(user.getPhone());
								use.setType(use.getType());
								use.setFacdes(user.getFacdes());
								fUtils.writeData(list, "users.data");
												
								return true;
							}
						}
					}
					
				}
			}	
		}			
		return false;
	}
	

	
	public List<User> showUser() throws IOException {
		FileUtils2<List<User>> fUtils = new FileUtils2<>();
		List<User> list = fUtils.getData("users.data");
		
		//update data
		if(list == null)
		{
			list = new ArrayList<>();
			return list;
		}
		return list;
	}
	
}
