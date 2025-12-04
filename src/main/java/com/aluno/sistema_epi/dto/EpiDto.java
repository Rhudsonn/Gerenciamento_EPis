package com.aluno.sistema_epi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpiDto {

    @NotBlank(message = "Nome do epi não pode ser vazio !")
    @Size(max = 100, message = "Nome deve ter no maximo 100 caracteres !")
    private String nomeEpi;

    @NotBlank(message = "Código não pode ser vazio !")
    @Size(max = 50, message = "O código não pode ultrapassar 50 caracteres !")
    private String codigoEpi;

    @NotBlank(message = "Descrição não pode ser vazio !")
    @Size(max = 2000) // Para garantir contro - mas no banco ta TEXT então eu nao preciso coloca um maximo de caracteres.
    private String descricaoEpi;

    @NotBlank(message = "Data de validade não pode ser vazia !")
    @Future // Use para datas que devem obrigatoriamente estar no futuro.
    private LocalDate dataValidade;

    @Positive(message = "O estoque deve ser maior que 0 !") // Aceita apenas números maiores que 0
    private Integer estoque;

    @NotBlank(message = "Categoria não pode ser vazio !")
    @Size(max = 50, message = "Categoria deve ter no maximo 50 caracteres !")
    private String categoria;


}
