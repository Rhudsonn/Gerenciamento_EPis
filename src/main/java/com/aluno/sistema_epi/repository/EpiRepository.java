package com.aluno.sistema_epi.repository;

import com.aluno.sistema_epi.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpiRepository extends JpaRepository<ColaboradorEntity, Integer> {

}
