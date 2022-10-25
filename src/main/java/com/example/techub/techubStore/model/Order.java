package com.example.techub.techubStore.model;

import java.time.LocalDate;

public class Order {
	private Integer id;
	private ClientTechub client;
	private LocalDate order_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ClientTechub getClient() {
		return client;
	}
	public void setClient(ClientTechub client) {
		this.client = client;
	}
	public LocalDate getOrder_date() {
		return order_date;
	}
	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}
	
	
}
