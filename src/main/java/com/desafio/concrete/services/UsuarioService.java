package com.desafio.concrete.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.concrete.domain.Usuario;
import com.desafio.concrete.exceptions.UsuarioNaoEncontradoException;
import com.desafio.concrete.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Usuario consultarCadastroUsuario(String email) throws UsuarioNaoEncontradoException {
		Optional<Usuario> obj = repo.findByEmail(email);
		return obj.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário e/ou senha inválidos"));
	}
}
