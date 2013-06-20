package data;

import java.sql.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class BudgetData{

	private static String _name;
	private static HashMap<Date,Transaction> _transactions;
	private static Date _startDate, _lastSaveDate;
	
	public static void setup(){
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + _name + ".db");
	      System.out.println("Opened/Created database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE TRANSACTIONS " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " TIME           TEXT    NOT NULL, " + 
	                   " VALUE          INT     NOT NULL, " + 
	                   " MEMO           TEXT)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	}
	
	public static void insert(Transaction t){
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + _name + ".db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      SimpleDateFormat format = new SimpleDateFormat("EEE, MMMM dd, hh:mm aa");
	      String sql = "INSERT INTO COMPANY (ID,TIME,VALUE,MEMO) " +
	                   "VALUES (1,  " + format.format(t.getDate()) + ", '" + t.getValue() + "', " + t.getDescription() + " );"; 
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Records created successfully");
	}
	
	public static void load(String name){
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + _name + ".db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
	      while ( rs.next() ) {
	         int id = rs.getInt("id");
	         String  time = rs.getString("time");
	         int value  = rs.getInt("value");
	         String  memo = rs.getString("memo");
	         System.out.println( "ID = " + id );
	         System.out.println( "TIME = " + time );
	         System.out.println( "VALUE = " + value );
	         System.out.println( "MEMO = " + memo );
	         System.out.println();
	         
	         put(new Transaction(value, new SimpleDateFormat("EEE, MMMM dd, hh:mm aa", Locale.ENGLISH).parse(time), memo));
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	}
	
	public static void put(String name){
		_name = name;
	}
	public static void put(Transaction transaction){
		_transactions.put(transaction.getDate(), transaction);
	}
	public static void put(Date date, boolean isStartDate){
		if (isStartDate){
			_startDate = date;
		}
		else 
			_lastSaveDate = date;
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
