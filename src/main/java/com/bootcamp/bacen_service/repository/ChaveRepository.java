package com.bootcamp.bacen_service.repository;

import com.bootcamp.bacen_service.model.Chave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChaveRepository extends JpaRepository<Chave, UUID> {
    boolean existsByChave(final String chave);
    Optional<Chave> findByChave(final String chavePesquisada);
}
