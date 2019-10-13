package com.previsaotempo.app.dto;

import java.io.Serializable;
import java.util.List;

public class ClimaCidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cidade;
	private String estado;
	private String pais;
	
	private List<SituacaoClimaticaDTO> situacoesClimaticas;

	public ClimaCidadeDTO() {
		
	}

	public ClimaCidadeDTO(String cidade, String estado, String pais, List<SituacaoClimaticaDTO> situacoesClimaticas) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.situacoesClimaticas = situacoesClimaticas;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<SituacaoClimaticaDTO> getClima() {
		return situacoesClimaticas;
	}

	public void setClima(List<SituacaoClimaticaDTO> clima) {
		this.situacoesClimaticas = clima;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClimaCidadeDTO [cidade=");
		builder.append(cidade);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", pais=");
		builder.append(pais);
		builder.append(", situacoesClimaticas=");
		builder.append(situacoesClimaticas);
		builder.append("]");
		return builder.toString();
	}	
	
	

}
