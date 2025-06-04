package com.aluguelcarros.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String marca;
    private int ano;
    private String cor;
    private String placa;
    private double precoAluguelPorDia;

    @Enumerated(EnumType.STRING)
    private StatusCarro status;
}

