package com.aluno.sistema_epi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorDto {

    @NotBlank(message = "Nome não pode ser vazio !")  // @NotBlank só funciona para String.
    @Size(max = 100, message = "Nome não pode ultrapassar 100 caracteres !")
    private String nomeColaborador;

    @NotBlank(message = "Matricola não pode ser vazio !")
    @Size(max = 50, message = "Matricola não pode ultrapassar 50 caracteres !")
    private String matricolaColaborador;

    @NotBlank(message = "Setor não pode ser vazio !")
    @Size(max = 100, message = "Setor não pode ultrapassar 100 caracteres !")
    private String setorColaborador;

    @NotBlank(message = "Telefone não pode ser vazio !")
    @Size(max = 20, message = "O telefone não póde ultrapassar 20 caracteres !")
    private String telefoneColaborador;

    @NotBlank(message = "Email não pode ser vazio !")
    @Email(message = "Email invalido !")
    private String emailColaborador;
}
