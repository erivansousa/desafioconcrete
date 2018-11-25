package com.desafio.concrete.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.concrete.domain.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
