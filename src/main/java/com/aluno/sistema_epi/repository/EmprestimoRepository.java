package com.aluno.sistema_epi.repository;

import com.aluno.sistema_epi.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<ColaboradorEntity, Integer> {

}
