package service;

import java.io.IOException;
import java.util.List;

import entity.User;


public interface UserService extends BaseService {
	public boolean validate(String id,String password);
	public boolean deleteUser(String id) throws IOException;
	public boolean addUser(User user) throws IOException;
	public User searchUser(String id) throws IOException;
	public boolean changeUser(User user) throws IOException;
	public List<User> showUser() throws IOException;
}
