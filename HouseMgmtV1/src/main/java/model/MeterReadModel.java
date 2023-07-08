package model;

import java.sql.Date;
import java.time.LocalDateTime;

// generate of constructor
//on eclipse click source then generate constructor using the fields
public class MeterReadModel {



	 public int getReading() {
		return reading;
	}
	public void setReading(int reading) {
		this.reading = reading;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
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
	public int getEntryIDMR() {
		return entryIDMR;
	}
	public void setEntryIDMR(int entryIDMR) {
		this.entryIDMR = entryIDMR;
	}
	public MeterReadModel(int reading, Date date1, String notes, int housenum, int entryIDMR) {
		super();
		this.reading = reading;
		this.date1 = date1;
		this.notes = notes;
		this.housenum = housenum;
		this.entryIDMR = entryIDMR;
	}

	public MeterReadModel(int reading2, LocalDateTime dateTime, String notes2, int housenum2, int entryIDMR2) {
		// TODO Auto-generated constructor stub
	}

	private int reading;
	 private Date date1;
	 private String notes;
	 private int housenum;
	 private int entryIDMR;
	 
	 
	 

	 // generate of constructor

}
