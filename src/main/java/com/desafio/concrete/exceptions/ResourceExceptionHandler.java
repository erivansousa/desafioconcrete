package com.desafio.concrete.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<StandardError> objectNotFound(UsuarioNaoEncontradoException e, HttpServletRequest request) {
		StandardError err = new StandardError(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(UsuarioNaoAutenticadoException.class)
	public ResponseEntity<StandardError> autenticacaoNaoAutorizada(UsuarioNaoAutenticadoException e, HttpServletRequest request) {
		StandardError err = new StandardError(e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
