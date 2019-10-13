package com.previsaotempo.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.previsaotempo.app.model.ClimaCidade;

@Repository
public interface ClimaCidadeRepository extends JpaRepository<ClimaCidade, Integer> {
	
	@Query("SELECT clima FROM ClimaCidade clima WHERE clima.dataCidade.dataPrevisao BETWEEN :dataInicial AND :dataFinal ORDER BY clima.dataCidade.dataPrevisao ASC")
	@Transactional(readOnly=true)
	public List<ClimaCidade> findClimasCidades(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

}
