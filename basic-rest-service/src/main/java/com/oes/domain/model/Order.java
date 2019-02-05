package com.oes.domain.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

	private int id;
	private int total;
	private LocalDate date;
	private Customer customer;
	private List<LineItem> lineItems = new LinkedList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean addLineItem(LineItem lineItem) {
		return lineItems.add(lineItem);
	}

	public boolean removeLineItem(LineItem lineItem) {
		return lineItems.remove(lineItem);
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	
}
