package com.aluno.sistema_epi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ConsoleUtils {

 public static LocalDate localDate(String date){
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
         try{
             return LocalDate.parse(date, fml);
     }catch (Exception e){
             // tenta o proximo
         }
     }
     throw new IllegalArgumentException("Formato de data invalido: " + date);
 }




    public static void exibirMenu(){
        System.out.print("\n1) Cadastrar Colaborador \n");
        System.out.print("2) Listar Colaborador \n");
        System.out.print("3) Atualizar Colaborador \n");
        System.out.print("4) Deletar Colaborador \n\n");

        System.out.print("5) Cadastrar EPI \n");
        System.out.print("6) Listar EPI \n");
        System.out.print("7) Atualizar EPI \n");
        System.out.print("8) Deletar EPI \n\n");

        System.out.print("9) Cadastrar Emprestimo \n");
        System.out.print("10) Listar Emprestimo \n");
        System.out.print("11) Atualizar Emprestimo \n");
        System.out.print("12) Deletar Emprestimo \n\n");

        System.out.print("0) Sair\n");
    }



}
