package com.previsaotempo.app.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.previsaotempo.app.model.Cidade;

public class AnaliseClimaticaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CidadeMaxTemperaturaDTO cidadeMaximaTemperatura;
	private Map<String, Double> mediaPrecipitacaoCidade;

	public AnaliseClimaticaDTO() {
		mediaPrecipitacaoCidade = new HashMap<>();
	}
	
	public CidadeMaxTemperaturaDTO getCidadeMaximaTemperatura() {
		return cidadeMaximaTemperatura;
	}

	public void setCidadeMaximaTemperatura(CidadeMaxTemperaturaDTO cidadeMaximaTemperatura) {
		this.cidadeMaximaTemperatura = cidadeMaximaTemperatura;
	}
	
	public Map<String, Double> getMediaPrecipitacaoCidade() {
		return mediaPrecipitacaoCidade;
	}

	public void setMediaPrecipitacaoCidade(Map<String, Double> mediaPrecipitacaoCidade) {
		this.mediaPrecipitacaoCidade = mediaPrecipitacaoCidade;
	}
	

}
