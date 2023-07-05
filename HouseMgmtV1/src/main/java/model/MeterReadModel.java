package model;

import java.util.Date;
// generate of constructor
//on eclipse click source then generate constructor using the fields
public class MeterReadModel {
	
	
	public MeterReadModel(int reading, Date date, String notes, int housenum) {
		this.reading = reading;
		this.date = date;
		this.notes = notes;
		this.housenum = housenum;
	}
	private int reading;
	 private Date date;
	 private String notes;
	 private int housenum;

	 public int getReading() {
		return reading;
	}
	public void setReading(int reading) {
		this.reading = reading;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getHousenum() {
		return housenum;
	}
	public void setHousenum(int housenum) {
		this.housenum = housenum;
	}

	 
	 // generate of constructor

}
