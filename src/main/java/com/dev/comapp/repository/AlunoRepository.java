package com.dev.comapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.comapp.models.Aluno;
import com.dev.comapp.models.Post;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}