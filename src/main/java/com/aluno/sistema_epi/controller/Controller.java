package com.aluno.sistema_epi.controller;

import com.aluno.sistema_epi.dto.ColaboradorDto;
import com.aluno.sistema_epi.dto.EmprestimoDto;
import com.aluno.sistema_epi.dto.EpiDto;
import com.aluno.sistema_epi.enums.StatusEmprestimo;
import com.aluno.sistema_epi.service.ColaboradorService;
import com.aluno.sistema_epi.service.EmprestimoService;
import com.aluno.sistema_epi.service.EpiService;
import com.aluno.sistema_epi.util.ConsoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.aluno.sistema_epi.util.ConsoleUtils.localDate;

@Component
@RequiredArgsConstructor
public class Controller implements CommandLineRunner {

    private final ColaboradorService colaboradorService;

    private final EmprestimoService emprestimoService;

    private final EpiService epiService;

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
                    case 4 -> deletarColaborador();

                    case 5 -> cadastrarEpi();
                    case 6 -> listarEpi();
                    case 7 -> atualizarEpi();
                    case 8 -> deletarEpi();

                    case 9 -> cadastrarEmprestimo();
                    case 10 -> listarEmprestimos();
                    case 11 -> atualizarEmprestimo();
                    case 12 -> deletarEmprestimo();

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
    }


    public void cadastraColaborador(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome Colaborador: ");
        String nomeColaborador = sc.nextLine();
        System.out.println("Matricula: ");
        String matricula = sc.nextLine();
        System.out.println("Setor: ");
        String setor = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();

        ColaboradorDto colaboradorDto = new ColaboradorDto();

        colaboradorDto.setNomeColaborador(nomeColaborador);
        colaboradorDto.setMatricolaColaborador(matricula);
        colaboradorDto.setSetorColaborador(setor);
        colaboradorDto.setTelefoneColaborador(telefone);
        colaboradorDto.setEmailColaborador(email);

        colaboradorService.cadastraColaborador(colaboradorDto);

        System.out.println("Colaborador cadastrado com sucesso!");
    }

    public void listaColaboradores(){
        List<ColaboradorDto> listaColaboradores = colaboradorService.listaColaboradores();

        if (listaColaboradores.isEmpty()){
            System.out.println("Não há colaboradores !.");
        }

        System.out.println("Lista de colaboradores: ");

        for (ColaboradorDto c : listaColaboradores){

            System.out.println(" Nome: " + c.getNomeColaborador());
            System.out.println(" Matricula: " + c.getMatricolaColaborador());
            System.out.println(" Setor: " + c.getSetorColaborador());
            System.out.println(" Telefone: " + c.getTelefoneColaborador());
            System.out.println(" Email: " + c.getEmailColaborador());
        }
    }

    public void atualizarColaborador(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID do colaborador que deseja atualizar:");
        Integer id = sc.nextInt();
        System.out.println("Novo nome: ");
        String nomeColaborador = sc.next();
        System.out.println("Nova matricula: ");
        String matricula = sc.next();
        System.out.println("Novo setor: ");
        String setor = sc.next();
        System.out.println("Novo Telefone: ");
        String telefone = sc.next();
        System.out.println("Novo Email: ");
        String email = sc.next();

        ColaboradorDto colaboradorDto = new ColaboradorDto();

        colaboradorDto.setNomeColaborador(nomeColaborador);
        colaboradorDto.setMatricolaColaborador(matricula);
        colaboradorDto.setSetorColaborador(setor);
        colaboradorDto.setTelefoneColaborador(telefone);
        colaboradorDto.setEmailColaborador(email);

        colaboradorService.atualizarColaborador(id, colaboradorDto);

        System.out.println("Colaborador atualizado com sucesso!");
    }

    public void deletarColaborador(){
        Scanner sc = new Scanner(System.in);

        System.out.println(" Insira o id do colaborador: ");
        Integer id = sc.nextInt();

        colaboradorService.deletarColaborador(id);

        System.out.println("Colaborador deletado com sucesso!");
    }

    //________________________________________________________________________________________

    public void cadastrarEpi(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome: ");
        String nomeEpi = sc.next();
        System.out.println("Codigo: ");
        String codigoEpi = sc.next();
        System.out.println("Descricao: ");
        String descricaoEpi = sc.next();
        System.out.println("ValidadeEpi: ");
        LocalDate validadeEpi = localDate(sc.next());
        System.out.println("Estoque: ");
        Integer estoqueEpi = sc.nextInt();
        System.out.println("Categoria: ");
        String categoriaEpi = sc.next();

        EpiDto epiDto = new EpiDto();

        epiDto.setNomeEpi(nomeEpi);
        epiDto.setCodigoEpi(codigoEpi);
        epiDto.setDescricaoEpi(descricaoEpi);
        epiDto.setValidade(validadeEpi);
        epiDto.setEstoque(estoqueEpi);
        epiDto.setCategoria(categoriaEpi);

        epiService.cadastrarEpi(epiDto);
    }


    public void listarEpi(){
        List<EpiDto> listaEpi = epiService.listarEpis();

        if (listaEpi.isEmpty()){
            System.out.println("Não a EPIs !.");
        }
        System.out.println("Lista de Epi: ");

            for (EpiDto e : listaEpi){
                System.out.println("Nome: " + e.getNomeEpi());
                System.out.println("Codigo: " + e.getCodigoEpi());
                System.out.println("Descricao: " + e.getDescricaoEpi());
                System.out.println("Validade: "+ e.getValidade());
                System.out.println("Estoque: " + e.getEstoque());
                System.out.println("Categoria: " + e.getCategoria());
            }

    }


    public void atualizarEpi(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID do EPI que deseja atualizar:");
        Integer id = sc.nextInt();
        sc.nextLine();

        System.out.println("Novo nome: ");
        String nomeEpi = sc.next();
        System.out.println("Novo Codigo: ");
        String codigoEpi = sc.next();
        System.out.println("Nova Descricao: ");
        String descricaoEpi = sc.next();

        System.out.println("Nova data de alidadeEpi: ");
        String validadeDigitada = sc.next();
        LocalDate validadeEpi = localDate(validadeDigitada);

        System.out.println("Atualizar estoque: ");
        Integer estoqueEpi = sc.nextInt();
        System.out.println("Atualizar categoria: ");
        String categoriaEpi = sc.next();

        EpiDto epiDto = new EpiDto();

        epiDto.setNomeEpi(nomeEpi);
        epiDto.setCodigoEpi(codigoEpi);
        epiDto.setDescricaoEpi(descricaoEpi);
        epiDto.setValidade(validadeEpi);
        epiDto.setEstoque(estoqueEpi);
        epiDto.setCategoria(categoriaEpi);

        epiService.atualizarEpi(id, epiDto);
    }


    private void deletarEpi(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID do EPI que deseja deletar: ");
        Integer id = sc.nextInt();

        epiService.deletarEpi(id);

        System.out.println("EPI deletado com sucesso!");
    }

    //__________________________________________________________________________________________

    public void cadastrarEmprestimo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("ID colaborador :");
        Integer idColaborador = sc.nextInt();
        System.out.println("ID Epi :");
        Integer idEpi = sc.nextInt();

        System.out.println("Data retirada: ");
        String dataRetiradaDigitada = sc.next();
        LocalDate dataRetirada = localDate(dataRetiradaDigitada);

        System.out.println("Data Prevista para devolução: ");
        String dataPrevistaDevolucao = sc.next();
        LocalDate dataPrevista = localDate(dataPrevistaDevolucao);

        System.out.println("Data da devolução: ");
        String devolucaoDigitada = sc.next();
        LocalDate dataDevolucao = null;
        if (devolucaoDigitada != null &&
                !devolucaoDigitada.equalsIgnoreCase("null") &&
                !devolucaoDigitada.isBlank()) {

            dataDevolucao = localDate(devolucaoDigitada);
        }

        System.out.println("Status do Empréstimo (EMPRESTADO / DEVOLVIDO / ATRASADO): ");
        String statusDigitado = sc.next();
        StatusEmprestimo statusEmprestimo = StatusEmprestimo.valueOf(statusDigitado.toUpperCase());

        System.out.println("Observações: ");
        sc.nextLine();
        String observacoes = sc.nextLine();

        EmprestimoDto emprestimoDto = new EmprestimoDto();

        emprestimoDto.setIdColaborador(idColaborador);
        emprestimoDto.setIdEpi(idEpi);
        emprestimoDto.setDataRetirada(dataRetirada);
        emprestimoDto.setDataPrevista(dataPrevista);
        emprestimoDto.setDataDevolucao(dataDevolucao);
        emprestimoDto.setStatusEmprestimo(statusEmprestimo);
        emprestimoDto.setObservacoes(observacoes);

        emprestimoService.cadastraEmprestimo(emprestimoDto);

        System.out.println("Emprestimo cadastrado com sucesso!");
    }


    public void listarEmprestimos(){
        List<EmprestimoDto> listaEmprestimos = emprestimoService.listaEmprestimos();

        if (listaEmprestimos.isEmpty()){
            System.out.println("Não a emprestimos !.");
        }

        System.out.println("Lista de Emprestimos: ");

        for (EmprestimoDto e : listaEmprestimos){
            System.out.println("Colaborador: " + e.getNomeColaborador() + " (ID: " + e.getIdColaborador() + ")");
            System.out.println("EPI: " + e.getNomeEpi() + " (ID: " + e.getIdEpi() + ")");
            System.out.println("Data Retirada: " + e.getDataRetirada());
            System.out.println("Data Prevista para entrega: " + e.getDataPrevista());
            System.out.println("Data Devolvido: " + e.getDataDevolucao());
            System.out.println("Status: " + e.getStatusEmprestimo());
            System.out.println("Observações: " + e.getObservacoes());
        }
    }


    public void atualizarEmprestimo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID do Emprestimo que deseja atualizar: ");
        Integer id = sc.nextInt();
        System.out.println("Id do Colaborador: ");
        Integer idColaborador = sc.nextInt();
        System.out.println("Id do Epi: ");
        Integer idEpi = sc.nextInt();

        System.out.println("Data de Retirada: ");
        String dataRetiradaDigitada = sc.next();
        LocalDate dataRetirada = localDate(dataRetiradaDigitada);

        System.out.println("Data Prevista para devolução: ");
        String dataPrevistaDevolucao = sc.next();
        LocalDate dataPrevista = localDate(dataPrevistaDevolucao);

        System.out.println("Data de devolucao (ou deixe vazio): ");
        String devolucaoDigitada = sc.next();

        LocalDate dataDevolucao = null;
        if (devolucaoDigitada != null && !devolucaoDigitada.equalsIgnoreCase("null") &&
                !devolucaoDigitada.isBlank()) {
            dataDevolucao = localDate(devolucaoDigitada);
        }

        System.out.println("Status do Emprestimo (EMPRESTADO / DEVOLVIDO / ATRASADO): ");
        String statusDigitado = sc.next();
        StatusEmprestimo statusEmprestimo = StatusEmprestimo.valueOf(statusDigitado.toUpperCase());

        System.out.println("Observacoes: ");
        sc.nextLine();
        String observacoes = sc.nextLine();

        EmprestimoDto emprestimoDto = new EmprestimoDto();

        emprestimoDto.setIdColaborador(idColaborador);
        emprestimoDto.setIdEpi(idEpi);
        emprestimoDto.setDataRetirada(dataRetirada);
        emprestimoDto.setDataPrevista(dataPrevista);
        emprestimoDto.setDataDevolucao(dataDevolucao);
        emprestimoDto.setStatusEmprestimo(statusEmprestimo);
        emprestimoDto.setObservacoes(observacoes);

        emprestimoService.atualizarEmprestimo(id, emprestimoDto);

        System.out.println("Emprestimo atualizado com sucesso!");
    }

    public void deletarEmprestimo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID do emprestimo que deseja deletar: ");
        Integer id = sc.nextInt();

        emprestimoService.deletarEmprestimo(id);

        System.out.println("Emprestimo deletado com sucesso!");
    }




}
