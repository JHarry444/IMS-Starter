package com.qa.ims.persistence.domain;

public class Item {
	
	private Long item_id;
	private String title;
	private float value;
	
	public Item(String title, float value) {
		this.setTitle(title);
		this.setValue(value);
	}
	

	public Item(Long item_id, String title, float value) {
		super();
		this.item_id = item_id;
		this.title = title;
		this.value = value;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "item_id:" + item_id + " title:" + title + " value:" + value;
	}

}
