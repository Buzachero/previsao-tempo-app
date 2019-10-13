package com.previsaotempo.app.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.previsaotempo.app.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
	@Transactional(readOnly=true)
	public Pais findBySigla(String sigla);

}
