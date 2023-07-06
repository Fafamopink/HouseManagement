package model;

import java.util.Date;
// generate of constructor
//on eclipse click source then generate constructor using the fields
public class MeterReadModel {


	public MeterReadModel(int reading, Date date1, String notes, int housenum,int entryIDMR) {
		this.reading = reading;
		this.date1 = date1;
		this.notes = notes;
		this.housenum = housenum;
		this.entryIDMR = entryIDMR;
		
	}
	 private int reading;
	 private Date date1;
	 private String notes;
	 private int housenum;
	 
	 
	 
	 public int getEntryIDMR() {
		return entryIDMR;
	}
	public void setEntryIDMR(int entryIDMR) {
		this.entryIDMR = entryIDMR;
	}
	private int entryIDMR;

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


	 // generate of constructor

}
