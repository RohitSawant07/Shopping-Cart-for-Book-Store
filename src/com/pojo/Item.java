package com.pojo;

public class Item {
	
	private Books books = new Books();
	private int quantity;
	
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Item(Books books, int quantity) {
		super();
		this.books = books;
		this.quantity = quantity;
	}
	
	public Item() {
		super();
	}
	
	
	

}
