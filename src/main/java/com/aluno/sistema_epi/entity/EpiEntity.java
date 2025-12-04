package com.aluno.sistema_epi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "epi")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEpi;

    @Column(name = "nome")
    private String nomeEpi;

    @Column(name = "codigo")
    private String codigoEpi;

    @Column(name = "descricao")
    private String descricaoEpi;

    @Column(name = "validade")
    private LocalDate validadeEpi;

    @Column(name = "estoque")
    private Integer estoqueEpi;

    @Column(name = "categoria")
    private String categoriaEpi;
}
