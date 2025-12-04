package com.aluno.sistema_epi.service;

import com.aluno.sistema_epi.dto.EpiDto;
import com.aluno.sistema_epi.entity.EpiEntity;
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
public class EpiService {


    private final EpiRepository epiRepository;

    // Criar Epi
    public EpiEntity cadastrarEpi(@Valid EpiDto epiDto){
        EpiEntity epiEntity = new EpiEntity();

        preencherEpi(epiEntity,epiDto);  // As informaçoes estão no metodo auxiliar

        return epiRepository.save(epiEntity);
    }

    // Listar todos Epis
    public List<EpiEntity> listarEpis(){
        List<EpiEntity> listaEpiEntity = epiRepository.findAll();
        List<EpiDto> listaEpiDto = new ArrayList<>();

        for (EpiEntity e : listaEpiEntity) {
            EpiDto epiDto = new EpiDto();

            epiDto.setNomeEpi(e.getNomeEpi());
            epiDto.setCodigoEpi(e.getCodigoEpi());
            epiDto.setDescricaoEpi(e.getDescricaoEpi());
            epiDto.setValidade(e.getValidadeEpi());
            epiDto.setEstoque(e.getEstoqueEpi());
            epiDto.setCategoria(e.getCategoriaEpi());

            listaEpiDto.add(epiDto);
        }
        return listaEpiEntity;
    }

    // Buscar Epi por ID
    public EpiEntity buscarEpiPorId(Integer id){
        return epiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" EPI não encontrado com o ID: " +id));
        // EntityNotFoundException = é uma exceção padrão da JPA (Jakarta Persistence), usada quando você tenta buscar algo no banco de dados mas esse registro não existe.
    }

    //Atualizar Epi
    public EpiEntity atualizarEpi(Integer id, @Valid EpiDto epiDto){
        EpiEntity epiEntity = epiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EPI não foi encontrado !."));

        preencherEpi(epiEntity,epiDto);  // As informaçoes estão no metodo auxiliar

        return epiRepository.save(epiEntity);
    }

    // Deletar Epi
    public void deletarEpi(Integer id){
        epiRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("EPI não foi encontrado !."));

        epiRepository.deleteById(id);
    }

    //  Metodo auxiliar (Refatoração)
    // Esse metodo é um metodo auxiliar de refatoração, criado para evitar código duplicado dentro da classe EpiService.
    private void preencherEpi(EpiEntity epiEntity, EpiDto epiDto){

        epiEntity.setNomeEpi(epiDto.getNomeEpi());
        epiEntity.setCodigoEpi(epiDto.getCodigoEpi());
        epiEntity.setDescricaoEpi(epiDto.getDescricaoEpi());
        epiEntity.setValidadeEpi(epiDto.getValidade());
        epiEntity.setEstoqueEpi(epiDto.getEstoque());
        epiEntity.setCategoriaEpi(epiDto.getCategoria());

    }


}
