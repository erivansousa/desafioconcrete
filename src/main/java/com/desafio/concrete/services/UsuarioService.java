package com.desafio.concrete.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.concrete.controlers.model.LoginRequest;
import com.desafio.concrete.controlers.model.ProfileRequestInfo;
import com.desafio.concrete.domain.Telefone;
import com.desafio.concrete.domain.Usuario;
import com.desafio.concrete.exceptions.EmailJaCadastradoException;
import com.desafio.concrete.exceptions.SessaoInvalidaException;
import com.desafio.concrete.exceptions.TokenNaoEncontradoException;
import com.desafio.concrete.exceptions.UsuarioNaoAutenticadoException;
import com.desafio.concrete.exceptions.UsuarioNaoEncontradoException;
import com.desafio.concrete.repositories.TelefoneRepository;
import com.desafio.concrete.repositories.UsuarioRepository;

import static com.desafio.concrete.util.Constantes.*;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usrRepo;

	@Autowired
	private TelefoneRepository telRepo;

	/**
	 * Cadastra um novo usuario no banco de dados e retorna o perfil do usuario gerado.	 * 
	 * Caso o e-mail ja exista, retorna erro com a mensagem "E-mail ja existente"
	 * 
	 * @param email
	 * @return
	 * @throws UsuarioNaoEncontradoException
	 */
	public Usuario cadastrarUsuario(Usuario usuario) throws UsuarioNaoEncontradoException {
		
		// variaveis locais
		Usuario usr;
		
		// Verificar se o email ja foi cadastrado
		// ---------------------
		usr = usrRepo.findByEmail(usuario.getEmail()).orElse(null);
		
		if (usr != null) {
			throw new EmailJaCadastradoException("E-mail ja existente");
		}
		// ---------------------
		
		// persiste o usuario
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
	 * Caso o e-mail e a senha correspondam a um usuario existente, retornar igual ao endpoint de Criacao; 
	 * Caso o e-mail nao exista, retornar erro com status apropriado mais a mensagem "Usuario e/ou senha invalidos";
	 * Caso o e-mail exista mas a senha nao bata, retornar o status apropriado 401 mais a mensagem "Usuario e/ou senha invalidos";
	 * 
	 * @param email
	 * @return
	 * @throws UsuarioNaoEncontradoException
	 */
	public Usuario login(LoginRequest loginInfo) throws UsuarioNaoEncontradoException {

		// verifica se o email esta cadastrado
		Optional<Usuario> obj = usrRepo.findByEmail(loginInfo.getEmail());

		// Caso nao tenha sido encontrado cadastro com o email informado, lanca excessao
		Usuario usr = obj.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario e/ou senha invalidos"));

		// Existindo cadastro, verifica se a senha informada corresponde a senha
		// cadastrada
		if (!usr.getPassword().equals(loginInfo.getPassword())) {
			throw new UsuarioNaoAutenticadoException("Usuario e/ou senha invalidos");
		}

		// atualiza a data de ultimo acesso
		usr.setLastLogin(LocalDateTime.now());
		usr.generateToken();

		usrRepo.save(usr);

		return usr;
	}
	
	/**
	 * 
	 * Caso o token nao exista, retornar erro com status apropriado com a mensagem "Nao autorizado".
	 * Caso o token exista, buscar o usuario pelo id passado no path e comparar se o token no modelo e igual ao token passado no header.
	 * Caso nao seja o mesmo token, retornar erro com status apropriado e mensagem "Nao autorizado"
	 * Caso seja o mesmo token, verificar se o ultimo login foi a MENOS que 30 minutos atras. Caso nao seja a MENOS que 30 minutos atras, 
	 * retornar erro com status apropriado com mensagem "Sessao invalida".
	 * 
	 * @param profileInfo
	 * @return
	 */
	public Usuario perfil(ProfileRequestInfo profileInfo) {
		// verifica se o token informado existe
		Optional<Usuario> obj = usrRepo.findByToken(UUID.fromString(profileInfo.getToken()));

		// Caso nao tenha sido encontrado nenhum token, lanca excessao
		Usuario usr = obj.orElseThrow(() -> new TokenNaoEncontradoException("Nao Autorizado"));
		
		if(!usr.getId().equals(UUID.fromString(profileInfo.getUsrId()))) {
			throw new SessaoInvalidaException(HttpStatus.UNAUTHORIZED, "Nao Autorizado");
		}
		
		if(LocalDateTime.now().minusMinutes(SESSION_TIMEOUT).compareTo(usr.getLastLogin()) > 0) {
			throw new SessaoInvalidaException(HttpStatus.GONE, "Sessao invalida");
		}
		
		
		return usr;
	}


	/**
	 * Persiste o usuario no banco e garante que as datas sejam geradas assim como o primeiro token de acesso
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
