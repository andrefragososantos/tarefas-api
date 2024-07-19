package com.tarefas.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.api.entities.Tarefa;
import com.tarefas.api.services.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefa() {
        return ResponseEntity.ok().body(tarefaService.listarTarefa());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(tarefa.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleterUsuarioPorId(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tarefaService.deletarTarefa(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tarefaAtualizada.setId(id);
        return ResponseEntity.ok().body(tarefaService.salvarTarefa(tarefaAtualizada));
    }


    
    
}
