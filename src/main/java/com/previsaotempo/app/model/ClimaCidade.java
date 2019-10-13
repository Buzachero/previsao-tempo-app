package com.previsaotempo.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIMA_CIDADE")
public class ClimaCidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private DataCidadePK dataCidade = new DataCidadePK();
	
	private Integer probabilidadeChuva;
	private Integer precipitacao;	
	
	private Integer temperaturaMinima;
	private Integer temperaturaMaxima;
	
	public ClimaCidade() {
		
	}

	public ClimaCidade(Cidade cidade, LocalDate dataPrevisao, Integer probabilidadeChuva, Integer precipitacao,
			Integer temperaturaMinima, Integer temperaturaMaxima) {
		super();
		this.dataCidade.setCidade(cidade);
		this.dataCidade.setDataPrevisao(dataPrevisao);
		this.probabilidadeChuva = probabilidadeChuva;
		this.precipitacao = precipitacao;
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
	}	

	public DataCidadePK getDataCidade() {
		return dataCidade;
	}

	public void setDataCidade(DataCidadePK dataCidade) {
		this.dataCidade = dataCidade;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCidade == null) ? 0 : dataCidade.hashCode());
		result = prime * result + ((precipitacao == null) ? 0 : precipitacao.hashCode());
		result = prime * result + ((probabilidadeChuva == null) ? 0 : probabilidadeChuva.hashCode());
		result = prime * result + ((temperaturaMaxima == null) ? 0 : temperaturaMaxima.hashCode());
		result = prime * result + ((temperaturaMinima == null) ? 0 : temperaturaMinima.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClimaCidade other = (ClimaCidade) obj;
		if (dataCidade == null) {
			if (other.dataCidade != null)
				return false;
		} else if (!dataCidade.equals(other.dataCidade))
			return false;
		if (precipitacao == null) {
			if (other.precipitacao != null)
				return false;
		} else if (!precipitacao.equals(other.precipitacao))
			return false;
		if (probabilidadeChuva == null) {
			if (other.probabilidadeChuva != null)
				return false;
		} else if (!probabilidadeChuva.equals(other.probabilidadeChuva))
			return false;
		if (temperaturaMaxima == null) {
			if (other.temperaturaMaxima != null)
				return false;
		} else if (!temperaturaMaxima.equals(other.temperaturaMaxima))
			return false;
		if (temperaturaMinima == null) {
			if (other.temperaturaMinima != null)
				return false;
		} else if (!temperaturaMinima.equals(other.temperaturaMinima))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClimaCidade [dataCidade=");
		builder.append(dataCidade);
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
