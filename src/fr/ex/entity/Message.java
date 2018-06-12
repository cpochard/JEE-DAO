package fr.ex.entity;

import java.sql.Date;

public class Message {
	
	private long userID;
	private long id;
	private String text;
	private Date date;
	
	public Message() {
		super();
	}
	
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [userID=");
		builder.append(userID);
		builder.append(", id=");
		builder.append(id);
		builder.append(", text=");
		builder.append(text);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
	

}
