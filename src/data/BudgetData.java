package data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class BudgetData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6571026606285048160L;
	private static String _name;
	private static HashMap<Date,Transaction> _transactions;
	private static Date _startDate, _lastSaveDate;
	
	public static void put(String name){
		_name = name;
	}
	
	public static void put(Date date, Transaction transaction){
		_transactions.put(date, transaction);
	}
	
	public static void put(Date date, boolean isStartDate){
		if (isStartDate){
			_startDate = date;
		}
		else 
			_lastSaveDate = date;
	}
	
	public static void put(BudgetData data){
		setName(data.getName());
		setTransactions(data.getTransactions());
		setStartDate(data.getStartDate());
		setLastSaveDate(data.getLastSaveDate());
	}
	
	public static String getName(){
		return _name;
	}
	public static void setName(String name){
		_name = name;
	}
	
	public static HashMap<Date,Transaction> getTransactions(){
		return _transactions;
	}
	public static void setTransactions(HashMap<Date, Transaction> map){
		_transactions = map;
	}
	
	public static Date getStartDate(){
		return _startDate;
	}
	public static void setStartDate(Date date){
		_startDate = date;
	}
	
	public static Date getLastSaveDate(){
		return _lastSaveDate;
	}
	public static void setLastSaveDate(Date date){
		_lastSaveDate = date;
	}


}
