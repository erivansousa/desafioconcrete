package com.desafio.concrete.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logon")
public class LogonController {
	
	@RequestMapping(value="/cadastro", method = RequestMethod.GET)
	public ResponseEntity<?> cadastro() {
		return null;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ResponseEntity<?> login() {
		return null;
	}
	
	@RequestMapping(value="/perfil", method = RequestMethod.GET)
	public ResponseEntity<?> perfil() {
		return null;
	}

}
