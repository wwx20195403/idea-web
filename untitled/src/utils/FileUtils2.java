package utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileUtils2<E> {
	
	public E getData(String filename)
	{

//		String path = this.getClass().getClassLoader().getResource("com/neuedu/files/"+filename).getPath();				    
//		File f = new File(path);
		File f = new File("src/files/"+filename);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			if(!f.exists())f.createNewFile();
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			E root = (E)ois.readObject();
			return root;
		}
		catch (EOFException e1) {
			
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						 		
		return null;
	}
	
	public void writeData(E data,String filename)
	{
		//
//		String path = this.getClass().getClassLoader().getResource("com/neuedu/files/"+filename).getPath();	
//	    
//	    File f = new File(path);
		File f = new File("src/files/"+filename);
	    ObjectOutputStream oos = null;
	    try {
	    	if(!f.exists())f.createNewFile();
			FileOutputStream fis = new FileOutputStream(f);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(data);
			oos.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	    	try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

}
