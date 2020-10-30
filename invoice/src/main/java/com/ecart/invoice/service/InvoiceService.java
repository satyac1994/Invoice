package com.ecart.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecart.invoice.entity.InvoiceEntity;
import com.ecart.invoice.model.Invoice;
import com.ecart.invoice.model.Order;
import com.ecart.invoice.repository.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoiceService {
	private String ecartShotUrl = "http://localhost:8080";
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private InvoiceRepository invoiceRepository;

	public Invoice createInvoice(Order order) {
		log.info("Invoice generation started for "+order.getOrderId());
		Order fullOrder = getOrderDetails(order);
		if (null == fullOrder) {
			throw new RuntimeException("no order details found");
		}
		InvoiceEntity invoiceEntity = invoiceRepository.save(createInvoiceEntity(order));
		
		return constructInvoice(invoiceEntity, fullOrder);
	}

	private Invoice constructInvoice(InvoiceEntity invoiceEntity, Order fullOrder) {
		Invoice invoice = new Invoice();
		invoice.setDate(invoiceEntity.getInvoiceDate());
		invoice.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
		invoice.setOrder(fullOrder);
		log.info("Invoice generation completed for "+ fullOrder.getOrderId() +" invoice number is "+invoiceEntity.getInvoiceNumber());
		return invoice;
	}

	private InvoiceEntity createInvoiceEntity(Order order) {
		InvoiceEntity entity = new InvoiceEntity();
		entity.setOrderId(order.getOrderId());
		return entity;
	}

	private Order getOrderDetails(Order order) {
		return (restTemplate.getForEntity(ecartShotUrl + "/orders/" + order.getOrderId(), Order.class)).getBody();
	}

}
