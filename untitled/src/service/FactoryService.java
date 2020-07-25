package service;

import java.io.IOException;
import java.util.List;

import entity.Factory;

public interface FactoryService extends BaseService{
	public List<Factory> showFactory() throws IOException;
	public boolean changeFactorystate(String userID,String choice) throws IOException ;
	public Factory sreachFactory(String userID) throws IOException ;
}
