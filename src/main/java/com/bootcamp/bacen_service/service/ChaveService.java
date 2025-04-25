package com.bootcamp.bacen_service.service;

import com.bootcamp.bacen_service.dto.ChaveRequestDTO;
import com.bootcamp.bacen_service.dto.ChaveResponseDTO;
import com.bootcamp.bacen_service.repository.ChaveRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import com.bootcamp.bacen_service.model.Chave;
@Component //faz o spring entender q essa classe é um componente (não precisa criar instâncias do obj)
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class ChaveService {
    //colocar os métodos (de criar e consultar chave)
    private final ChaveRepository chaveRepository;

    public ChaveResponseDTO criarChave(final ChaveRequestDTO chaveRequestDTO) {
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

    public ChaveResponseDTO buscarChave(final String chavePesquisada) {
        Chave chave = chaveRepository
                .findByChave(chavePesquisada)
                .orElseThrow(() -> new RuntimeException());

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }
}

    /*
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

}
*/
