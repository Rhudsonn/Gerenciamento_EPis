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

    @NotBlank(message = "Data da retirada não pode ser vazia !")
    @PastOrPresent
    private LocalDate dataRetirada;

    @NotBlank(message = "Data prevista não pode ser vazio !")
    @FutureOrPresent
    private LocalDate dataPrevista;

    @NotBlank(message = "Data da devolução não pode ser vazio !")
    @FutureOrPresent
    private LocalDate dataDevolucao;

    @NotBlank(message = "Status do emprestimo não pode ser vazio !")
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo statusEmprestimo;

    @NotBlank(message = "A observação não pode ser vazio !")
    @Size(max = 2000) // Para garantir contro - mas no banco ta TEXT então eu nao preciso coloca um maximo de caracteres.
    private String observacoes;


}
