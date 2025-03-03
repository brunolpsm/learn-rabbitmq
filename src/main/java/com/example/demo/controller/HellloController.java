package com.example.demo.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController implements Serializable {

	private static final long serialVersionUID = 1L;

	@GetMapping
	public Object test() {
		return "TESTANDO ";
	}

}
