package br.senai.sc.engajamento.nome.reacao.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BuscarUmReacaoCommand {
    private UUID idReacao;
}