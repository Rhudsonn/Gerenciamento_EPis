package com.aluno.sistema_epi.service;

import com.aluno.sistema_epi.dto.EmprestimoDto;
import com.aluno.sistema_epi.entity.ColaboradorEntity;
import com.aluno.sistema_epi.entity.EmprestimoEntity;
import com.aluno.sistema_epi.entity.EpiEntity;
import com.aluno.sistema_epi.repository.ColaboradorRepository;
import com.aluno.sistema_epi.repository.EmprestimoRepository;
import com.aluno.sistema_epi.repository.EpiRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final EpiRepository epiRepository;


    // Criar Emprestimo
    public EmprestimoEntity cadastraEmprestimo(@Valid EmprestimoDto emprestimoDto) {

        ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(emprestimoDto.getIdColaborador())
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado !."));

        EpiEntity epiEntity = epiRepository.findById(emprestimoDto.getIdEpi())
                .orElseThrow(() -> new EntityNotFoundException("EPI não encontrado !."));

        EmprestimoEntity emprestimoEntity = new EmprestimoEntity();

        preencherEmprestimo(emprestimoEntity,emprestimoDto,colaboradorEntity,epiEntity);  // As informaçoes estão no metodo auxiliar

        return emprestimoRepository.save(emprestimoEntity);
    }

    // Listar todos Emprestimos
    public List<EmprestimoDto> listaEmprestimos(){
        List<EmprestimoEntity> listaEmprestimoEntity = emprestimoRepository.findAll();
        List<EmprestimoDto> listaEmprestimoDto = new ArrayList<>();

        for (EmprestimoEntity e : listaEmprestimoEntity) {
            EmprestimoDto emprestimoDto = new EmprestimoDto();

            emprestimoDto.setDataRetirada(e.getDataRetirada());
            emprestimoDto.setDataPrevista(e.getDataPrevista());
            emprestimoDto.setDataDevolucao(e.getDataDevolucao());
            emprestimoDto.setStatusEmprestimo(e.getStatusEmprestimo());
            emprestimoDto.setObservacoes(e.getObservacoes());

            listaEmprestimoDto.add(emprestimoDto);
        }
        return listaEmprestimoDto;
    }

    // Buscar Emprestimo por ID
    public EmprestimoEntity buscarPorId(Integer id){
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Emprestimo não encontrado com o ID: " +id));
        // EntityNotFoundException = é uma exceção padrão da JPA (Jakarta Persistence), usada quando você tenta buscar algo no banco de dados mas esse registro não existe.
    }

    //Atualizar Emprestimo
    public  EmprestimoEntity atualizarEmprestimo(Integer id, @Valid EmprestimoDto emprestimoDto) {
        EmprestimoEntity emprestimoEntity = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado !."));

        ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(emprestimoDto.getIdColaborador())
                        .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado !."));

        EpiEntity epiEntity = epiRepository.findById(emprestimoDto.getIdEpi())
                        .orElseThrow(() -> new EntityNotFoundException("EPI não encontrado !."));

        preencherEmprestimo(emprestimoEntity, emprestimoDto, colaboradorEntity, epiEntity); // As informaçoes estão no metodo auxiliar

        return emprestimoRepository.save(emprestimoEntity);
    }

    // Deletar Emprestimo
    public void deletarEmprestimo(Integer id) {
        EmprestimoEntity emprestimoEntity = buscarPorId(id);
        emprestimoRepository.delete(emprestimoEntity);
    }

    //  Metodo auxiliar (Refatoração)
    // Esse metodo é um metodo auxiliar de refatoração, criado para evitar código duplicado dentro da classe EmprestimoService.
    private void preencherEmprestimo(EmprestimoEntity emprestimoEntity, EmprestimoDto emprestimoDto,
                                     ColaboradorEntity colaboradorEntity, EpiEntity epiEntity) {

        emprestimoEntity.setColaborador(colaboradorEntity);
        emprestimoEntity.setEpi(epiEntity);
        emprestimoEntity.setDataRetirada(emprestimoDto.getDataRetirada());
        emprestimoEntity.setDataPrevista(emprestimoDto.getDataPrevista());
        emprestimoEntity.setDataDevolucao(emprestimoDto.getDataDevolucao());
        emprestimoEntity.setStatusEmprestimo(emprestimoDto.getStatusEmprestimo());
        emprestimoEntity.setObservacoes(emprestimoDto.getObservacoes());

    }


}
