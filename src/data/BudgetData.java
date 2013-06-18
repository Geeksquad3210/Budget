package data;

import java.util.Date;
import java.util.HashMap;

public class BudgetData {
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
}
