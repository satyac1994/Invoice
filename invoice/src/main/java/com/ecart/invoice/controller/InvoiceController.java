package com.ecart.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecart.invoice.model.Invoice;
import com.ecart.invoice.model.Order;
import com.ecart.invoice.service.InvoiceService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/invoice")
@EnableSwagger2
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping
	public Invoice createInvoice(@RequestBody Order order) {
		return invoiceService.createInvoice(order);
	}

}
