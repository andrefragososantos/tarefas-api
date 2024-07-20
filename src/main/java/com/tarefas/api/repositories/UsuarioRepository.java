package com.tarefas.api.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeLike(String nome);
    
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFim);
    
}
