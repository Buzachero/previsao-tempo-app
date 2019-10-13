package com.previsaotempo.app.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.previsaotempo.app.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	@Transactional(readOnly=true)
	public Cidade findByNome(String nome);

}
