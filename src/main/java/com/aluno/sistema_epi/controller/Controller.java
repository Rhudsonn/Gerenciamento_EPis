package com.aluno.sistema_epi.controller;

import com.aluno.sistema_epi.dto.ColaboradorDto;
import com.aluno.sistema_epi.dto.EmprestimoDto;
import com.aluno.sistema_epi.dto.EpiDto;
import com.aluno.sistema_epi.service.ColaboradorService;
import com.aluno.sistema_epi.service.EmprestimoService;
import com.aluno.sistema_epi.service.EpiService;
import com.aluno.sistema_epi.util.ConsoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Scanner;


@Component
@RequiredArgsConstructor
public class Controller implements CommandLineRunner {

    private final ColaboradorService colaboradorService;

    private final EmprestimoService emprestimoService;

    private final EpiService epiService;

    // Aqui adicionei este Scanner para evitar duplica scanner para todas as vezes que precisar ler algo.
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run(String... args){
        Scanner sc = new Scanner(System.in);

        int opcao;

        System.out.println("\n Spring sem Web \n");

        do {
            ConsoleUtils.exibirMenu();

            opcao = sc.nextInt();

            try {
                switch (opcao) {
                    case 1 -> cadastraColaborador();
                    case 2 -> listaColaboradores();
                    case 3 -> atualizarColaborador();
                    case 4 -> buscarColaboradorPorId();
                    case 5 -> deletarColaborador();

                    case 6 -> cadastrarEpi();
                    case 7 -> listarEpi();
                    case 8 -> atualizarEpi();
                    case 9 -> buscarEpiPorId();
                    case 10 -> deletarEpi();

                    case 11 -> cadastrarEmprestimo();
                    case 12 -> listarEmprestimos();
                    case 13 -> atualizarEmprestimo();
                    case 14 -> buscarEmprestimoPorId();
                    case 15 -> deletarEmprestimo();

                    case 0 -> System.out.println("Saindo da aplicação...");

                    default -> {
                        System.out.println("Opção invalida \n");
                        ConsoleUtils.exibirMenu();
                        opcao = sc.nextInt();
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (opcao != 0);

        sc.close();
    }


    public void cadastraColaborador(){

        ColaboradorDto colaboradorDto = ConsoleUtils.criarColaboradorDto(sc); // Metodo auxiliar esta na classe ConsoleUtills
        colaboradorService.cadastraColaborador(colaboradorDto);
        System.out.println("Colaborador cadastrado com sucesso!\n");
    }

    public void listaColaboradores(){
        System.out.println("Lista de colaboradores: \n");

        var lista = colaboradorService.listaColaboradores();
        lista.forEach(ConsoleUtils::imprimirColaborador);
    }

    public void atualizarColaborador(){

        Integer id = ConsoleUtils.lerInteger(sc,"Id do colaborador: ");
        ColaboradorDto colaboradorDto = ConsoleUtils.criarColaboradorDto(sc);
        colaboradorService.atualizarColaborador(id, colaboradorDto);

        System.out.println("Colaborador atualizado com sucesso!\n");
    }

    public void buscarColaboradorPorId(){

        Integer id = ConsoleUtils.lerInteger(sc,"Insira o ID do colaborador que deseja buscar: ");

        // Chama o service (lançará EntityNotFoundException se não existir)
        var c = colaboradorService.buscarPorId(id);

        System.out.println(" Colaborador encontrado : \n");
        System.out.println(" ID do colaborador: " + c.getIdColaborador());
        System.out.println(" Nome: " + c.getNomeColaborador());
        System.out.println(" Matricula: " + c.getMatricolaColaborador());
        System.out.println(" Setor: " + c.getSetorColaborador());
        System.out.println(" Telefone: " + c.getTelefoneColaborador());
        System.out.println(" Email: " + c.getEmailColaborador() + "\n");
    }


    public void deletarColaborador(){

        System.out.println(" Insira o id do colaborador que deseja deletar: ");
        Integer id = sc.nextInt();

        colaboradorService.deletarColaborador(id);

        System.out.println("Colaborador deletado com sucesso! \n");
    }

    //________________________________________________________________________________________

    public void cadastrarEpi(){


        EpiDto epiDto = ConsoleUtils.criarEpiDto(sc); // Metodo auxiliar esta na classe ConsoleUtills
        epiService.cadastrarEpi(epiDto);

        System.out.println("EPI cadastro com sucesso! \n");
    }


    public void listarEpi(){

        System.out.println("Lista de Epi: \n");

        var lista = epiService.listarEpis();
        lista.forEach(ConsoleUtils::imprimirEpi); // Metodo auxiliar esta na classe ConsoleUtills

    }


    public void atualizarEpi(){

        // Metodo auxiliar esta na classe ConsoleUtills
        Integer id = ConsoleUtils.lerInteger(sc, "Insira o ID do EPI que deseja atualizar: ");
        EpiDto epiDto = ConsoleUtils.criarEpiDto(sc);
        epiService.atualizarEpi(id, epiDto);

        System.out.println("Epi cadastro com sucesso! \n");
    }

    public void buscarEpiPorId(){

        System.out.println(" Insira o ID do EPI que deseja buscar: ");
        Integer id = sc.nextInt();

        // Chama o service (lançará EntityNotFoundException se não existir)
        var e = epiService.buscarEpiPorId(id);

        System.out.println(" EPI encontrado :\n");
        System.out.println(" ID do EPI: " + e.getIdEpi());
        System.out.println(" Nome do EPI: " + e.getNomeEpi());
        System.out.println(" Codigo: " + e.getCodigoEpi());
        System.out.println(" Descrição: " + e.getDescricaoEpi());
        System.out.println(" Validade: " + e.getValidadeEpi());
        System.out.println(" Estoque: " + e.getEstoqueEpi());
        System.out.println(" Categoria: " + e.getCategoriaEpi() + "\n");
    }



    private void deletarEpi(){

        System.out.println("Insira o ID do EPI que deseja deletar: ");
        Integer id = sc.nextInt();

        epiService.deletarEpi(id);

        System.out.println("EPI deletado com sucesso!\n");
    }

    //__________________________________________________________________________________________

    public void cadastrarEmprestimo(){

        EmprestimoDto emprestimoDto = ConsoleUtils.criarEmprestimoDto(sc); // Metodo auxiliar esta na classe ConsoleUtills
        emprestimoService.cadastraEmprestimo(emprestimoDto);

        System.out.println("Emprestimo cadastrado com sucesso!\n");
    }


    public void listarEmprestimos(){

        System.out.println("Lista de Emprestimos: \n");

        var lista = emprestimoService.listaEmprestimos(); // Metodo auxiliar esta na classe ConsoleUtills
        lista.forEach(ConsoleUtils::imprimirEmprestimo);
    }


    public void atualizarEmprestimo(){

        // Metodo auxiliar esta na classe ConsoleUtills
        Integer id = ConsoleUtils.lerInteger(sc, "Insira o ID do Emprestimo que deseja atualizar: ");
        EmprestimoDto emprestimoDto = ConsoleUtils.criarEmprestimoDto(sc);
        emprestimoService.atualizarEmprestimo(id, emprestimoDto);

        System.out.println("Emprestimo atualizado com sucesso!\n");
    }

    public void buscarEmprestimoPorId() {

        System.out.println(" Insira o ID do emprestimo que deseja buscar: ");
        Integer idEmprestimo = sc.nextInt();

        // Chama o service (lançará EntityNotFoundException se não existir)
        var e = emprestimoService.buscarEmprestimoPorId(idEmprestimo);

        System.out.println(" Emprestim encontrado :\n");
        System.out.println(" Data de retirada: " + e.getDataRetirada());
        System.out.println(" Data prevista para devolução: " + e.getDataPrevista());
        System.out.println(" Status: " + e.getStatusEmprestimo());
        System.out.println(" Observação: " + e.getObservacoes());
        System.out.println(" Colaborador pertencente ao emprestimo: " + e.getColaborador().getNomeColaborador());
        System.out.println(" Epi emprestado: " + e.getEpi().getNomeEpi() +"\n");
    }

    public void deletarEmprestimo(){

        System.out.println("Insira o ID do emprestimo que deseja deletar: ");
        Integer id = sc.nextInt();

        emprestimoService.deletarEmprestimo(id);

        System.out.println("Emprestimo deletado com sucesso!\n");
    }

}