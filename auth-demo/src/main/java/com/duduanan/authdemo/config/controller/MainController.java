package com.duduanan.authdemo.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@RequestMapping("/errors")
	public String error() {
		return "error";
	}
}
