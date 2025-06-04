package com.aluguelcarros.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;
}

    

