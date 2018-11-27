package com.desafio.concrete.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Tratamento das mensagens de erro por excecao.
 * 
 * @author erivan
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> autenticacaoNaoAutorizada(RuntimeException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err;
		
		if(e instanceof UsuarioNaoEncontradoException)
			status = HttpStatus.UNAUTHORIZED;
		else if(e instanceof UsuarioNaoAutenticadoException)
			status = HttpStatus.UNAUTHORIZED;
		else if(e instanceof EmailJaCadastradoException)
			status = HttpStatus.CONFLICT;
		else if(e instanceof TokenNaoEncontradoException)
			status = HttpStatus.UNAUTHORIZED;
		else if(e instanceof SessaoInvalidaException)
			status = ((SessaoInvalidaException)e).getStatus();
			
		err = new StandardError(e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> serverError(Exception e, HttpServletRequest request) {
		StandardError err = new StandardError(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
