package br.senai.sc.engajamento.inscricao.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.repository.InscricaoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InscricaoService {
    private final InscricaoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public void criar(@Valid CriarInscricaoCommand cmd) {
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));;
        Usuario canal = usuarioRepository.findById(cmd.getIdCanal())
                .orElseThrow(()-> new NaoEncontradoException("Canal não encontrado!"));;

        if (repository.findByIdUsuarioAndIdCanal(usuario, canal) == null) {
            Inscricao inscricao = new Inscricao();
            inscricao.setIdUsuario(usuario);
            inscricao.setIdCanal(canal);
            repository.save(inscricao);
        } else {
            repository.deleteByIdUsuarioAndIdCanal(usuario, canal);
        }
    }

    public Inscricao buscarUm(@Valid BuscarUmInscricaoCommand cmd) {
        Usuario usuario = usuarioRepository.findById(cmd.getIdUsuario())
                .orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado!"));;
        Usuario canal = usuarioRepository.findById(cmd.getIdCanal())
                .orElseThrow(()-> new NaoEncontradoException("Canal não encontrado!"));;

        Inscricao inscricao = repository.findByIdUsuarioAndIdCanal(usuario, canal);
        if (inscricao == null) {
            throw new NaoEncontradoException("Inscrição não encontrada!");
        }
        return inscricao;
    }
}
