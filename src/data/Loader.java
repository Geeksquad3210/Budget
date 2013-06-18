package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Loader implements Serializable{
	private static void saveData(BudgetData data, String filePath) throws IOException {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			outputStream.writeObject(data);
		} catch(FileNotFoundException ex){
			System.out.println("Error: Save data not found.");
			ex.printStackTrace();
		} catch(IOException ex){
			ex.printStackTrace();
		} finally {
			try {
				if(outputStream != null){
					outputStream.flush();
					outputStream.close();
				}
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public static BudgetData loadGame(String filePath) throws IOException{
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			return (BudgetData) in.readObject();
		} catch(FileNotFoundException ex){
			System.out.println("Error: Save data not found.");
			ex.printStackTrace();
			return null;
		} catch(IOException ex){
			ex.printStackTrace();
			return null;
		} catch(ClassNotFoundException ex){
			System.out.println("The programmer messed up this time.");
			ex.printStackTrace();
			return null;
		}
	}
}
