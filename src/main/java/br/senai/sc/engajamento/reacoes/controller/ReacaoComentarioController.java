package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.service.ReacaoComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoComentario")
public class ReacaoComentarioController {
    private final ReacaoComentarioService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarReacaoComentarioCommand cmd) {
        service.criar(cmd);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReacaoComentario> buscarUm(@RequestBody @Valid BuscarUmReacaoComentarioCommand cmd) {
        ReacaoComentario reacaoComentario = null;
        try {
            reacaoComentario = service.buscarUm(cmd);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return ResponseEntity.ok(reacaoComentario);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ReacaoComentario>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
}
