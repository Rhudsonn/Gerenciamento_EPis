package com.aluno.sistema_epi.service;

import com.aluno.sistema_epi.dto.ColaboradorDto;
import com.aluno.sistema_epi.entity.ColaboradorEntity;
import com.aluno.sistema_epi.repository.ColaboradorRepository;
import com.aluno.sistema_epi.repository.EmprestimoRepository;
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
public class ColaboradorService {


    private final ColaboradorRepository colaboradorRepository;

    private final EmprestimoRepository emprestimoRepository;


    // Criar Colaborador
    public void cadastraColaborador(@Valid ColaboradorDto colaboradorDto) {
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();

        preencherColaborador(colaboradorEntity, colaboradorDto);  // As informaçoes estão no metodo auxiliar

        colaboradorRepository.save(colaboradorEntity);
    }

    // Listar todos Colaboradores
    public List<ColaboradorDto> listaColaboradores() {
        List<ColaboradorEntity> listarColaboradorEntity = colaboradorRepository.findAll();
        List<ColaboradorDto> listaColaboradorDto = new ArrayList<>();

        for (ColaboradorEntity c : listarColaboradorEntity) {
            ColaboradorDto colaboradorDto = new ColaboradorDto();

            colaboradorDto.setIdColaborador(c.getIdColaborador());

            colaboradorDto.setNomeColaborador(c.getNomeColaborador());
            colaboradorDto.setMatricolaColaborador(c.getMatricolaColaborador());
            colaboradorDto.setSetorColaborador(c.getSetorColaborador());
            colaboradorDto.setTelefoneColaborador(c.getTelefoneColaborador());
            colaboradorDto.setEmailColaborador(c.getEmailColaborador());

            listaColaboradorDto.add(colaboradorDto);
        }
        return listaColaboradorDto;
    }

    // Buscar colaborador por ID
    public ColaboradorEntity buscarPorId(Integer id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado com o ID: " + id));
        // EntityNotFoundException = é uma exceção padrão da JPA (Jakarta Persistence), usada quando você tenta buscar algo no banco de dados mas esse registro não existe.
    }


    //Atualizar Colaborador
    public void atualizarColaborador(Integer id, @Valid ColaboradorDto colaboradorDto) {
        ColaboradorEntity colaboradorEntity = colaboradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado (Id não encontrado)!. "));

        preencherColaborador(colaboradorEntity, colaboradorDto);  // As informaçoes estão no metodo auxiliar

        colaboradorRepository.save(colaboradorEntity);
    }

    // Deletar Colaborador
    public void deletarColaborador(Integer id) {
        colaboradorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Colaborador não existe !."));

        if (emprestimoRepository.existsById(id)) {
            throw new EntityNotFoundException("Não pode deletar Colaborador com emprestimos !.");
        }
        colaboradorRepository.deleteById(id);
    }

    //  Metodo auxiliar (Refatoração)
    // Esse metodo é um metodo auxiliar de refatoração, criado para evitar código duplicado dentro da classe ColaboradorService.
    private void preencherColaborador(ColaboradorEntity colaboradorEntity, ColaboradorDto colaboradorDto) {

        colaboradorEntity.setNomeColaborador(colaboradorDto.getNomeColaborador());
        colaboradorEntity.setMatricolaColaborador(colaboradorDto.getMatricolaColaborador());
        colaboradorEntity.setSetorColaborador(colaboradorDto.getSetorColaborador());
        colaboradorEntity.setTelefoneColaborador(colaboradorDto.getTelefoneColaborador());
        colaboradorEntity.setEmailColaborador(colaboradorDto.getEmailColaborador());

    }




}
