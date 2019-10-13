package com.previsaotempo.app.dto;

import java.io.Serializable;

public class CidadeMaxTemperaturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeCidade;
	private Integer temperaturaMaxima;
	
	public CidadeMaxTemperaturaDTO() {		
		
	}
	
	public CidadeMaxTemperaturaDTO(String nomeCidade, Integer temperaturaMaxima) {		
		this.nomeCidade = nomeCidade;
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Integer getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(Integer temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	
	

}
