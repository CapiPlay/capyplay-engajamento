package br.senai.sc.engajamento.video.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarVideoPorIdCommand {
    private UUID idVideo;
}
