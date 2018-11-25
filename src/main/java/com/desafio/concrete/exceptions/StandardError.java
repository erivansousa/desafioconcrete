package com.desafio.concrete.exceptions;

import java.io.Serializable;

/**
 * Classe de mensagem de erro padrão pra manter o retorno das mensagens de erro
 * com o mesmo formato
 * 
 * @author erivan
 *
 */
public class StandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensagem;

	public StandardError(String msg) {
		super();
		this.mensagem = msg;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String msg) {
		this.mensagem = msg;
	}
}
