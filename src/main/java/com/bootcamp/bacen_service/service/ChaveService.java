package com.bootcamp.bacen_service.service;

import com.bootcamp.bacen_service.dto.ChaveRequestDTO;
import com.bootcamp.bacen_service.dto.ChaveResponseDTO;
import com.bootcamp.bacen_service.exception.ChaveJaCadastradaException;
import com.bootcamp.bacen_service.exception.ChaveNaoLocalizadaException;
import com.bootcamp.bacen_service.repository.ChaveRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.bootcamp.bacen_service.model.Chave;
import org.springframework.transaction.annotation.Transactional;

@Component //faz o spring entender q essa classe é um componente (não precisa criar instâncias do obj)
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class ChaveService {
    //colocar os métodos (de criar e consultar chave)
    private final ChaveRepository chaveRepository;

    @Transactional
    public ChaveResponseDTO criarChave(final ChaveRequestDTO chaveRequestDTO) {

        if(chaveRepository.existsByChave(chaveRequestDTO.getChave())) {
            throw new ChaveJaCadastradaException(
                    String.format("A chave: %s já existe no sistema.", chaveRequestDTO.getChave())
            );
        }

        Chave chave = Chave.builder()
                .chave(chaveRequestDTO.getChave())
                .ativa(chaveRequestDTO.getAtiva())
                .build();

        chave = chaveRepository.save(chave);

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }

    public ChaveResponseDTO buscarChave (final String chavePesquisada) {
        Chave chave = chaveRepository.findByChave(chavePesquisada).orElseThrow(
                () -> new ChaveNaoLocalizadaException(
                        String.format("A chave: %s não existe no sistema.", chavePesquisada)
                ));

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }

    public Chave buscarEntidadeChave(String chavePesquisada) {
        return chaveRepository.findByChave(chavePesquisada).orElseThrow(
                () -> new ChaveNaoLocalizadaException(
                        String.format("A chave: %s não existe no sistema.", chavePesquisada)
                ));
    }

    public void deletarChave(Chave chave) {
        chaveRepository.delete(chave);
    }

    @Transactional
    public ChaveResponseDTO atualizarChave(String chaveAtual, ChaveRequestDTO novosDados) {
        Chave chave = chaveRepository.findByChave(chaveAtual).orElseThrow(
                () -> new ChaveNaoLocalizadaException(
                        String.format("A chave: %s não existe no sistema.", chaveAtual)
                )
        );

        chave.setAtiva(novosDados.getAtiva());

        chave = chaveRepository.save(chave);

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }

}

