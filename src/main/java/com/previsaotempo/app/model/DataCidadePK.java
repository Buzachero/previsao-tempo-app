package com.previsaotempo.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DataCidadePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne	
    @JoinColumn(name = "cidade_id")        
	private Cidade cidade;
	@Column(name = "data_previsao")	
	private LocalDate dataPrevisao;	
	
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	/*
	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	*/
	public LocalDate getDataPrevisao() {
		return dataPrevisao;
	}
	
	public void setDataPrevisao(LocalDate dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((dataPrevisao == null) ? 0 : dataPrevisao.hashCode());
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
		DataCidadePK other = (DataCidadePK) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (dataPrevisao == null) {
			if (other.dataPrevisao != null)
				return false;
		} else if (!dataPrevisao.equals(other.dataPrevisao))
			return false;
		return true;
	}



	
}
