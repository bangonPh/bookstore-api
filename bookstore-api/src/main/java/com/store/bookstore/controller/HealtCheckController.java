package com.store.bookstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.bookstore.dto.HealtCheckDto;

@RestController
public class HealtCheckController {
	
	@RequestMapping(value = "/healtcheck")
	public HealtCheckDto healtCheck() {
		HealtCheckDto healtCheckDto = new HealtCheckDto();
		healtCheckDto.setServiceName("bookstore-api");
		healtCheckDto.setStatus("UP");
		return healtCheckDto;
	}

}
