package com.desafio.concrete.exceptions;

public class UsuarioNaoAutenticadoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNaoAutenticadoException(String msg) {
		super(msg);
	}
}
