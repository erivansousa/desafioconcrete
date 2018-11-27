package com.desafio.concrete.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.concrete.controlers.model.LoginRequest;
import com.desafio.concrete.controlers.model.ProfileRequestInfo;
import com.desafio.concrete.domain.Usuario;
import com.desafio.concrete.services.UsuarioService;

@RestController
@RequestMapping(value = "/logon")
public class LogonController {

	@Autowired
	UsuarioService usService;

	/**
	 * 
	 * @param loginRequest
	 * @return
	 */
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ResponseEntity<?> cadastro(@RequestBody Usuario usuario) {

		Usuario usr = usService.cadastrarUsuario(usuario);

		return ResponseEntity.ok().body(usr);
	}

	/**
	 * 
	 * @param loginRequest
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {

		Usuario user = usService.login(loginRequest);

		return ResponseEntity.ok().body(user);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public ResponseEntity<Usuario> perfil(@RequestBody ProfileRequestInfo profileInfo) {
		
		Usuario user = usService.perfil(profileInfo);

		return ResponseEntity.ok().body(user);
	}

}
