package com.desafio.concrete.controlers.model;

/**
 * Modelo da requisi��o do endpoint de login
 * 
 * {"email": "???", "password": "???"}
 * 
 * @author erivan
 *
 */
public class LoginRequest {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
