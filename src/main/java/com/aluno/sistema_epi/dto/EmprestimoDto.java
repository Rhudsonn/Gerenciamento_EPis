package com.aluno.sistema_epi.dto;

import com.aluno.sistema_epi.enums.StatusEmprestimo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDto {

    @NotNull(message = "A data da retirada é obrigatória!")
    @PastOrPresent  // não pode estar no futuro
    private LocalDate dataRetirada;

    @NotNull(message = "Data prevista não pode ser vazia !")
    @FutureOrPresent  // devolução tem que ser hoje ou depois
    private LocalDate dataPrevista;

    @NotNull(message = "Data da devolução não pode ser vazia !")
    @FutureOrPresent  // devolução tem que ser hoje ou depois
    private LocalDate dataDevolucao;

    @NotNull(message = "Status do emprestimo não pode ser vazio !")
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo statusEmprestimo;

    @NotBlank(message = "A observação não pode ser vazia !")  // @NotBlank só funciona para String.
    @Size(max = 2000) // Para garantir contro - mas no banco ta TEXT então eu nao preciso coloca um maximo de caracteres.
    private String observacoes;

    // IDs necessários para o relacionamento - Porque a Entity exige esse relacionamento.
    @NotNull(message = "O id do colaborador é obrigatório!")
    private Integer idColaborador;

    @NotNull(message = "O id do EPI é obrigatório!")
    private Integer idEpi;

    // Adicionei este campo para exibir os nome em listar emprestimo
    private String nomeColaborador;
    private String nomeEpi;




}
