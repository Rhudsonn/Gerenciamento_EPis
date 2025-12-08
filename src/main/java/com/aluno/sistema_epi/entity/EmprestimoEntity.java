package com.aluno.sistema_epi.entity;

import com.aluno.sistema_epi.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEmprestimo;

    // Relacionamento: Muitos empréstimos para um colaborador
    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradorEntity colaborador;

    // Relacionamento: Muitos empréstimos para um EPI
    @ManyToOne
    @JoinColumn(name = "epi_id", nullable = false)
    private EpiEntity epi;

    @Column(name = "data_retirada")
    private LocalDate dataRetirada;

    @Column(name = "data_prevista")
    private LocalDate dataPrevista;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private StatusEmprestimo statusEmprestimo;

    @Column(name = "observacoes")
    private String observacoes;



}
