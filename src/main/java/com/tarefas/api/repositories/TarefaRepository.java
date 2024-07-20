package com.tarefas.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.api.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByResponsavel_Id(Long id);

    List<Tarefa> findByTituloLike(String titulo);

    List<Tarefa> findByDataEntregaBetween(LocalDate dataInicio, LocalDate dataFim);
    
}
