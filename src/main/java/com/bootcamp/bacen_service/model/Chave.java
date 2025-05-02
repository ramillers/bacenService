package com.bootcamp.bacen_service.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chave {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String chave;

    @Column
    private Boolean ativa;
}
