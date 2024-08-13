package com.Model;

import javax.persistence.*;


@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int quantity;
	private String measure;
	private String category;
	
	
	
	public Item() {
		super();
	}
	
	public Item(String name) {
		this.name = name;
	}
	
	
	
	public Item(String name, String measure, String category) {
		super();
		this.name = name;
		this.measure = measure;
		this.category = category;
	}



	public Item(int id, String name, int quantity, String measure, String category) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.measure = measure;
		this.category = category;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getMeasure() {
		return measure;
	}



	public void setMeasure(String measure) {
		this.measure = measure;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	

	
	
	
	
}
