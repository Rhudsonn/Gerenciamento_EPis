package com.aluno.sistema_epi.util;

import com.aluno.sistema_epi.dto.ColaboradorDto;
import com.aluno.sistema_epi.dto.EmprestimoDto;
import com.aluno.sistema_epi.dto.EpiDto;
import com.aluno.sistema_epi.enums.StatusEmprestimo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ConsoleUtils {

    // Exibe o menu no terminal
    public static void exibirMenu() {
        System.out.print("1) Cadastrar Colaborador \n");
        System.out.print("2) Listar Colaborador \n");
        System.out.print("3) Atualizar Colaborador \n");
        System.out.println("4) Buscar Colaborador por ID: ");
        System.out.print("5) Deletar Colaborador \n\n");

        System.out.print("6) Cadastrar EPI \n");
        System.out.print("7) Listar EPI \n");
        System.out.print("8) Atualizar EPI \n");
        System.out.println("9) Buscar EPI por ID: ");
        System.out.print("10) Deletar EPI \n\n");

        System.out.print("11) Cadastrar Emprestimo \n");
        System.out.print("12) Listar Emprestimo \n");
        System.out.print("13) Atualizar Emprestimo \n");
        System.out.println("14) Buscar Emprestimo por ID: ");
        System.out.print("15) Deletar Emprestimo \n\n");

        System.out.print("0) Sair\n");
    }


    // Metodo auxiliar formata a data informada no terminal
    public static LocalDate localDate(String date) {
        date = date.trim(); //Remove espaços

        DateTimeFormatter[] formatos = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("ddMMyyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("dd.MM.yyyy"),
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy")
        };

        for (DateTimeFormatter fml : formatos) {
            try {
                return LocalDate.parse(date, fml);
            } catch (Exception e) {
                // tenta o proximo
            }
        }
        throw new IllegalArgumentException("Formato de data invalido: " + date);


    }


    // Coloquei este metodo para Ler Strings e evitar duplicar varias vezes elas no codigo.
    public static String lerString(Scanner sc, String mensagem) {
        System.out.println(mensagem);
        String entrada = sc.nextLine().trim();
        return entrada;
    }


    // Coloquei este metodo para ler INTEGERS.
    public static Integer lerInteger(Scanner sc, String mensagem) {
        System.out.println(mensagem);
        Integer entrada = Integer.parseInt(sc.nextLine().trim());
        return entrada;
    }


    // Coloquei este metodo para ler as datas.
    public static LocalDate lerdata(Scanner sc, String msg) {
        System.out.println(msg);
        return localDate(sc.nextLine().trim());
    }


    // Metodo auxiliar para Imprimir Colaborador asim evita duplicar elas no controller.
    public static void imprimirColaborador(ColaboradorDto colaborador) {
        System.out.println("ID: " + colaborador.getIdColaborador());
        System.out.println("Nome: " + colaborador.getNomeColaborador());
        System.out.println("Matricula: " + colaborador.getMatricolaColaborador());
        System.out.println("Setor: " + colaborador.getSetorColaborador());
        System.out.println("Telefone: " + colaborador.getTelefoneColaborador());
        System.out.println("Email: " + colaborador.getEmailColaborador()+"\n");
    }


    // Metodo auxiliar para Imprimir Epi asim evita duplicar elas no controller.
    public static void imprimirEpi(EpiDto epi) {
        System.out.println("ID: " + epi.getIdEpi());
        System.out.println("Nome: " + epi.getNomeEpi());
        System.out.println("Codigo: " + epi.getCodigoEpi());
        System.out.println("Descricao: " + epi.getDescricaoEpi());
        System.out.println("Validade: " + epi.getValidade());
        System.out.println("Estoque: " + epi.getEstoque());
        System.out.println("Categoria: " + epi.getCategoria()+"\n");
    }


    // Metodo auxiliar para Imprimir Emprestimo asim evita duplicar elas no controller.
    public static void imprimirEmprestimo(EmprestimoDto emprestimo) {
        System.out.println("Colaborador: " + emprestimo.getNomeColaborador());
        System.out.println("EPI: " + emprestimo.getNomeEpi());
        System.out.println("Data retirada: " + emprestimo.getDataRetirada());
        System.out.println("Data prevista: " + emprestimo.getDataPrevista());
        System.out.println("Data devolução: " + emprestimo.getDataDevolucao());
        System.out.println("Status: " + emprestimo.getStatusEmprestimo());
        System.out.println("Observações: " + emprestimo.getObservacoes()+"\n");
    }


    // Metodo que vai me auxiliar a imprimir e Criar ColaboradorDto evitando duplicadas.
    public static ColaboradorDto criarColaboradorDto(Scanner sc) {
        ColaboradorDto colaboradorDto = new ColaboradorDto();

        colaboradorDto.setNomeColaborador(lerString(sc, "Nome Colaborador:"));
        colaboradorDto.setMatricolaColaborador(lerString(sc, "Matricula:"));
        colaboradorDto.setSetorColaborador(lerString(sc, "Setor:"));
        colaboradorDto.setTelefoneColaborador(lerString(sc, "Telefone:"));
        colaboradorDto.setEmailColaborador(lerString(sc, "Email:"));

        return colaboradorDto;
    }


    // Metodo que vai me auxiliar a imprimir e Criar EpiDto evitando duplicadas.
    public static EpiDto criarEpiDto(Scanner sc) {
        EpiDto epiDto = new EpiDto();

        epiDto.setNomeEpi(lerString(sc, "Nome:"));
        epiDto.setCodigoEpi(lerString(sc, "Código:"));
        epiDto.setDescricaoEpi(lerString(sc, "Descrição:"));
        epiDto.setValidade(lerdata(sc, "Validade: "));
        epiDto.setEstoque(lerInteger(sc, "Estoque:"));
        epiDto.setCategoria(lerString(sc, "Categoria:"));

        return epiDto;
    }

    // Metodo que vai me auxiliar a imprimir e Criar Emprestimo evitando duplicadas.
    public static EmprestimoDto criarEmprestimoDto(Scanner sc) {
        EmprestimoDto emprestimoDto = new EmprestimoDto();

        emprestimoDto.setIdColaborador(lerInteger(sc, "ID do Colaborador:"));
        emprestimoDto.setIdEpi(lerInteger(sc, "ID do EPI:"));
        emprestimoDto.setDataRetirada(lerdata(sc, "Data retirada:"));
        emprestimoDto.setDataPrevista(lerdata(sc, "Data prevista para devolução:"));

        String devolucao = lerString(sc, "Data de devolução (ou deixe vazio): ");
        if (!devolucao.isBlank()) {
            emprestimoDto.setDataDevolucao(LocalDate.parse(devolucao));
        }

        emprestimoDto.setStatusEmprestimo(StatusEmprestimo.valueOf(lerString(sc,
                "Status (EMPRESTADO / DEVOLVIDO / ATRASADO): ").toUpperCase()));

        emprestimoDto.setObservacoes(lerString(sc, "Observacoes: "));

        return emprestimoDto;
    }


}

