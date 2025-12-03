package com.aluno.sistema_epi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "colaborador")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idColaborador;

    @Column(name = "nome")
    private String nomeColaborador;

    @Column(name = "matricula")
    private String matriculaColaborador;

    @Column(name = "setor")
    private String setorColaborador;

    @Column(name = "telefone")
    private String telefoneColaborador;

    @Column(name = "email")
    private String emailColaborador;

    // Relacionamento com Emprestimo
    @OneToMany(mappedBy = "colaborador")
    private List<EmprestimoEntity> emprestimos;


}
