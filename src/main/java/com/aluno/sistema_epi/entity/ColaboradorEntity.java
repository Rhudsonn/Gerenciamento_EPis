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
    private String matricolaColaborador;

    @Column(name = "setor")
    private String setorColaborador;

    @Column(name = "telefone")
    private String telefoneColaborador;

    @Column(name = "email")
    private String emailColaborador;

    // Relacionamento com Emprestimo
    @OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
    @ToString.Exclude  // evita StackOverflow no toString()
    @EqualsAndHashCode.Exclude  // evita problemas em equals/hashCode com coleções
    private List<EmprestimoEntity> emprestimos;


}
