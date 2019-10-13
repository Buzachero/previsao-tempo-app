package com.previsaotempo.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previsaotempo.app.dto.AnaliseClimaticaDTO;
import com.previsaotempo.app.dto.CidadeMaxTemperaturaDTO;
import com.previsaotempo.app.model.Cidade;
import com.previsaotempo.app.model.ClimaCidade;
import com.previsaotempo.app.repository.ClimaCidadeRepository;
import com.previsaotempo.app.service.exception.InvalidPeriod;

@Service
public class AnaliseClimaticaService {
	
	@Autowired
	private ClimaCidadeRepository climaCidadeRepository;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public AnaliseClimaticaDTO findClimasByDateRange(String dataInicial, String dataFinal) {		
		LocalDate initialDate = null;
		LocalDate finalDate = null;
		
		initialDate = LocalDate.parse(dataInicial, formatter);
		finalDate = LocalDate.parse(dataFinal, formatter);
		
		if(initialDate.isAfter(finalDate)) {
			throw new InvalidPeriod("Data inicial " + dataInicial + " é posterior a data final " + dataFinal);
		}
		
		List<ClimaCidade> climasCidade = climaCidadeRepository.findClimasCidades(initialDate, finalDate);
		
		Comparator<ClimaCidade> cidadeHighestTempComp = new Comparator<ClimaCidade>() {

			@Override
			public int compare(ClimaCidade clima1, ClimaCidade clima2) {				
				return clima1.getTemperaturaMaxima().compareTo(clima2.getTemperaturaMaxima());
			}
		};	
		
		Optional<ClimaCidade> climaCidadeMaxTemp = climasCidade.stream().max(cidadeHighestTempComp);
		
		Cidade cidadeMaxTemp = climaCidadeMaxTemp.get().getDataCidade().getCidade();
		Integer maxTemperature = climaCidadeMaxTemp.get().getTemperaturaMaxima();
		
		System.out.println("cidadeMaxTemp = " + cidadeMaxTemp.getNome() + "  maxTemperature = " + maxTemperature);
		
		AnaliseClimaticaDTO analiseClimatica = new AnaliseClimaticaDTO();
		
		analiseClimatica.setCidadeMaximaTemperatura(new CidadeMaxTemperaturaDTO(cidadeMaxTemp.getNome(), maxTemperature));
		
		Map<Cidade, List<ClimaCidade>> groupMap = climasCidade.stream().collect(Collectors.groupingBy(clima -> clima.getDataCidade().getCidade()));		
			
		Map<String, Double> analisePrecipitacaoMap = new HashMap<>();
				
		groupMap.forEach((keyCidade, valueClimasCidade) -> {
			analisePrecipitacaoMap.put(keyCidade.getNome(), valueClimasCidade.stream().collect(Collectors.averagingDouble(x -> x.getPrecipitacao())));
		});
		
		
		
		System.out.println(analisePrecipitacaoMap.toString());		
		
		analiseClimatica.setMediaPrecipitacaoCidade(analisePrecipitacaoMap);
		
		return analiseClimatica;
	}	

}
