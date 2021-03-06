package com.desafio.concrete.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.concrete.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

	@Query("SELECT u FROM Usuario u where u.email = :email") 
    public Optional<Usuario> findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM Usuario u where u.token = :token") 
    public Optional<Usuario> findByToken(@Param("token") UUID token);
}
