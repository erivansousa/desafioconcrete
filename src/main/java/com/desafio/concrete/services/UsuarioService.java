package com.desafio.concrete.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.concrete.controlers.model.LoginRequest;
import com.desafio.concrete.domain.Telefone;
import com.desafio.concrete.domain.Usuario;
import com.desafio.concrete.exceptions.EmailJaCadastradoException;
import com.desafio.concrete.exceptions.UsuarioNaoAutenticadoException;
import com.desafio.concrete.exceptions.UsuarioNaoEncontradoException;
import com.desafio.concrete.repositories.TelefoneRepository;
import com.desafio.concrete.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usrRepo;

	@Autowired
	private TelefoneRepository telRepo;

	/**
	 * 
	 * @param email
	 * @return
	 * @throws UsuarioNaoEncontradoException
	 */
	public Usuario login(LoginRequest loginInfo) throws UsuarioNaoEncontradoException {
		
		//verifica se o email está cadastrado
		Optional<Usuario> obj = usrRepo.findByEmail(loginInfo.getEmail());
		
		//Caso não tenha sido encontrado cadastro com o email informado, lança excessão
		Usuario usr = obj.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário e/ou senha inválidos"));
		
		//Existindo cadastro, verifica se a senha informada corresponde a senha cadastrada
		if(!usr.getPassword().equals(loginInfo.getPassword())) {
			throw new UsuarioNaoAutenticadoException("Usuário e/ou senha inválidos");
		}
		
		//atualiza a data de ultimo acesso
		usr.setLastLogin(LocalDateTime.now());
		usr.generateToken();
		
		usrRepo.save(usr);
				
		return usr;		
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws UsuarioNaoEncontradoException
	 */
	public Usuario cadastrarUsuario(Usuario usuario) throws UsuarioNaoEncontradoException {
		
		// variaveis locais
		Usuario usr;

		//Verificar se o email ja foi cadastrado
		// ---------------------
		usr = usrRepo.findByEmail(usuario.getEmail()).orElse(null);
		
		if(usr != null) {
			throw new EmailJaCadastradoException("E-mail já existente");
		}
		// ---------------------
		
		//persiste o usuario
		usr = this.salvarUsuario(usuario);

		// Associa e salva telefones caso tenham sido informados
		// ---------------------
		if (usuario.getPhones() != null && !usuario.getPhones().isEmpty()) {
			for (Telefone phone : usuario.getPhones()) {
				phone.setUsuario(usr);
			}
			telRepo.saveAll(usuario.getPhones());
		}
		// ---------------------
		return usr;
	}
	
	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws UsuarioNaoEncontradoException
	 */
	private Usuario salvarUsuario(Usuario usuario) throws UsuarioNaoEncontradoException {
		Usuario usr = null;
		
		usuario.setCreated(LocalDate.now());
		usuario.setModified(LocalDateTime.now());
		usuario.setLastLogin(LocalDateTime.now());

		usuario.generateToken();
		
		usr = usrRepo.save(usuario);
		
		return usr;
	}
}
