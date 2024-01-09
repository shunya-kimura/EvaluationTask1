package model.bean;

import java.sql.Timestamp;
import java.util.Date;

public class BookBean {
	
	private String JAN_CD;
	private String ISBN_CD;
	private String BOOK_NM;
	private String BOOK_KANA;
	private int PRICE;
	private Date ISSUE_DATE;
	private Timestamp CREATE_DATETIME;
	private Timestamp UPDATE_DATETIME;

	public BookBean() {}
	
	public BookBean(String JAN_CD, String ISBN_CD, String BOOK_NM, String BOOK_KANA, 
			int PRICE, Date ISSUE_DATE, Timestamp CREATE_DATETIME, Timestamp UPDATE_DATETIME) {
		this.JAN_CD = JAN_CD;
		this.ISBN_CD = ISBN_CD;
		this.BOOK_NM = BOOK_NM;
		this.BOOK_NM = BOOK_NM;
		this.PRICE = PRICE;
		this.ISSUE_DATE = ISSUE_DATE;
		this.CREATE_DATETIME = CREATE_DATETIME;
		this.UPDATE_DATETIME = UPDATE_DATETIME;
	}
	
	public String getJAN_CD() {
		return JAN_CD;
	}
	public void setJAN_CD(String JAN_CD) {
		this.JAN_CD = JAN_CD;
	}
	public String getISBN_CD() {
		return ISBN_CD;
	}
	public void setISBN_CD(String ISBN_CD) {
		this.ISBN_CD = ISBN_CD;
	}
	public String getBOOK_NM() {
		return BOOK_NM;
	}
	public void setBOOK_NM(String BOOK_NM) {
		this.BOOK_NM = BOOK_NM;
	}
	public String getBOOK_KANA() {
		return BOOK_KANA;
	}
	public void setBOOK_KANA(String BOOK_KANA) {
		this.BOOK_KANA = BOOK_KANA;
	}
	public int getPRICE() {
		return PRICE;
	}
	public void setPRICE(int PRICE) {
		this.PRICE = PRICE;
	}
	public Date getISSUE_DATE() {
		return ISSUE_DATE;
	}
	public void setISSUE_DATE(Date ISSUE_DATE) {
		this.ISSUE_DATE = ISSUE_DATE;
	}
	
	public Timestamp getCREATE_DATETIME() {
		return CREATE_DATETIME;
	}

	public void setCREATE_DATETIME(Timestamp CREATE_DATETIME) {
		this.CREATE_DATETIME = CREATE_DATETIME;
	}

	public Timestamp getUPDATE_DATETIME() {
		return UPDATE_DATETIME;
	}

	public void setUPDATE_DATETIME(Timestamp UPDATE_DATETIME) {
		this.UPDATE_DATETIME = UPDATE_DATETIME;
	}
	

}
