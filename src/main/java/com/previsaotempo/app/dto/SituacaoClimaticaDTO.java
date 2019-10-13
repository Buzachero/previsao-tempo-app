package com.previsaotempo.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonFormat;


public class SituacaoClimaticaDTO implements Serializable, Comparator<SituacaoClimaticaDTO> {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataPrevisao;
	
	private Integer probabilidadeChuva;
	private Integer precipitacao;	
	
	private Integer temperaturaMinima;
	private Integer temperaturaMaxima;
	
	public SituacaoClimaticaDTO() {
		
	}

	public SituacaoClimaticaDTO(LocalDate dataPrevisao, Integer probabilidadeChuva, Integer precipitacao,
			Integer temperaturaMinima, Integer temperaturaMaxima) {
		super();
		this.dataPrevisao = dataPrevisao;
		this.probabilidadeChuva = probabilidadeChuva;
		this.precipitacao = precipitacao;
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public LocalDate getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(LocalDate dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	public Integer getProbabilidadeChuva() {
		return probabilidadeChuva;
	}

	public void setProbabilidadeChuva(Integer probabilidadeChuva) {
		this.probabilidadeChuva = probabilidadeChuva;
	}

	public Integer getPrecipitacao() {
		return precipitacao;
	}

	public void setPrecipitacao(Integer precipitacao) {
		this.precipitacao = precipitacao;
	}

	public Integer getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(Integer temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Integer getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(Integer temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	
	@Override
	public int compare(SituacaoClimaticaDTO arg0, SituacaoClimaticaDTO arg1) {
		if(arg0 == null)
			return 1;
		if(arg1 == null)
			return -1;		
		
		LocalDate dt0 = (LocalDate) arg0.getDataPrevisao();
		LocalDate dt1 = (LocalDate) arg1.getDataPrevisao();
		
		return dt0.compareTo(dt1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SituacaoClimaticaDTO [dataPrevisao=");
		builder.append(dataPrevisao);
		builder.append(", probabilidadeChuva=");
		builder.append(probabilidadeChuva);
		builder.append(", precipitacao=");
		builder.append(precipitacao);
		builder.append(", temperaturaMinima=");
		builder.append(temperaturaMinima);
		builder.append(", temperaturaMaxima=");
		builder.append(temperaturaMaxima);
		builder.append("]");
		return builder.toString();
	}	
}
