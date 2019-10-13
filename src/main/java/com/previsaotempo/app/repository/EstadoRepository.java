package com.previsaotempo.app.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.previsaotempo.app.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	@Transactional(readOnly=true)
	public Estado findBySigla(String sigla);

}
