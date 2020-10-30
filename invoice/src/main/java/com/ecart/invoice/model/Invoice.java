package com.ecart.invoice.model;

import java.util.Date;

import lombok.Data;
@Data
public class Invoice {
	private long invoiceNumber;
	private Order order;
	private Date date;
	

}
