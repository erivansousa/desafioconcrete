package com.desafio.concrete.controlers.model;

/**
 * Modelo da requisição do endpoint de perfil
 * 
 *  {"usrId": "UUID", "token": "UUID"}
 *  
 * @author erivan
 *
 */
public class ProfileRequestInfo {

	private String usrId;
	private String token;

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String id) {
		this.usrId = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
