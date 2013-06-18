package data;

import java.util.Date;

public class Transaction {
	private int _value; //in cents!!!
	private Date _date; //idk how to use this
	private String _description;
	private boolean _isDeposit;
	
	public Transaction(int value, Date date, String description){
		_value = value;
		_date = date;
		_description = description;
		_isDeposit = value >= 0;
	}

	public Transaction(int value, String description){
		this(value, new Date(), description);
	}
	public Transaction(int value){
		this(value, new Date(), "");
	}
	
	public int getValue(){
		return _value;
	}
	public void setValue(int v){
		_value = v;
	}

	public Date getDate(){
		return _date;
	}
	public void setDate(Date d){
		_date = d;
	}
	
	public String getDescription(){
		return _description;
	}
	public void setDescription(String d){
		_description = d;
	}
	
	public boolean isDeposit(){
		return _isDeposit;
	}

	public void update(){
		_isDeposit = (_value >= 0);
	}
	
	
}
