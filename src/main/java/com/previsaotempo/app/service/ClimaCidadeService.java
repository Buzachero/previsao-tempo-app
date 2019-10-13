package com.previsaotempo.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previsaotempo.app.dto.ClimaCidadeDTO;
import com.previsaotempo.app.dto.SituacaoClimaticaDTO;
import com.previsaotempo.app.model.Cidade;
import com.previsaotempo.app.model.ClimaCidade;
import com.previsaotempo.app.model.Estado;
import com.previsaotempo.app.model.Pais;
import com.previsaotempo.app.repository.CidadeRepository;
import com.previsaotempo.app.repository.ClimaCidadeRepository;
import com.previsaotempo.app.repository.EstadoRepository;
import com.previsaotempo.app.repository.PaisRepository;
import com.previsaotempo.app.service.exception.ObjectNotFoundException;

@Service
public class ClimaCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;	
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private ClimaCidadeRepository climaCidadeRepository;
	@Autowired
	private RestService restService;
	
	private Comparator<SituacaoClimaticaDTO> dataPrevisaoComparator = new Comparator<SituacaoClimaticaDTO>() {

		@Override
		public int compare(SituacaoClimaticaDTO clima1, SituacaoClimaticaDTO clima2) {				
			return clima2.getDataPrevisao().compareTo(clima1.getDataPrevisao());
		}
	};	
	
	public ClimaCidadeDTO findByCidadeId(Integer cidadeId) throws IOException {
		
		List<ClimaCidade> climasCidade = restService.getClimaByCidadeId(cidadeId);		
		
		if(climasCidade != null) {
			climasCidade.forEach(clima -> System.out.println(clima));
		}		
		
		System.out.println(climasCidade.toString());
		
		Optional<ClimaCidadeDTO> climaCidadeDTO = toDTO(climasCidade);
		
		if(!climaCidadeDTO.isPresent()) {
			throw new ObjectNotFoundException("Cidade com id " + cidadeId + " não encontrada!");
		}
		
		persistToDatabase(climasCidade);		
		
		return climaCidadeDTO.get();		
	}	
	
	private void persistToDatabase(List<ClimaCidade> climasCidade) {	
		Cidade cidade = climasCidade.get(0).getDataCidade().getCidade();
		
		Pais paisDB = paisRepository.findBySigla(cidade.getEstado().getPais().getSigla());
		Integer paisId = paisDB != null ? paisDB.getId() : null;
		System.out.println("paisId = "+ paisId);
		cidade.getEstado().getPais().setId(paisId);
		
				
		Estado estadoDB = estadoRepository.findBySigla(cidade.getEstado().getSigla());
		Integer estadoId = estadoDB != null ? estadoDB.getId() : null;
		System.out.println("estadoId = "+ estadoId);
		cidade.getEstado().setId(estadoId);
		
		System.out.println("cidadeId = "+ cidade.getId());	
		
		paisRepository.save(cidade.getEstado().getPais());
		estadoRepository.save(cidade.getEstado());
		cidadeRepository.save(cidade);	
		
		climaCidadeRepository.saveAll(climasCidade); 				
	}

	
	
	public Optional<ClimaCidadeDTO> toDTO(List<ClimaCidade> climasCidade) {
		if(climasCidade == null || climasCidade.size() == 0) {
			return Optional.empty();
		}
		
		ClimaCidadeDTO climaCidadeDTO = new ClimaCidadeDTO();
		Cidade cidade = climasCidade.get(0).getDataCidade().getCidade();

		
		climaCidadeDTO.setCidade(cidade.getNome());
		climaCidadeDTO.setEstado(cidade.getEstado().getSigla());
		climaCidadeDTO.setPais(cidade.getEstado().getPais().getSigla());		
	
		List<SituacaoClimaticaDTO> climasDTO = new ArrayList<>();
		
		
		climasCidade.parallelStream().forEach(clima -> {
			SituacaoClimaticaDTO climaDTO = new SituacaoClimaticaDTO();
			climaDTO.setDataPrevisao(clima.getDataCidade().getDataPrevisao());
			climaDTO.setProbabilidadeChuva(clima.getProbabilidadeChuva());
			climaDTO.setPrecipitacao(clima.getPrecipitacao());		
			climaDTO.setTemperaturaMinima(clima.getTemperaturaMinima());
			climaDTO.setTemperaturaMaxima(clima.getTemperaturaMaxima());
			climasDTO.add(climaDTO);
		});
		
		climasDTO.sort(dataPrevisaoComparator);
		
		climaCidadeDTO.setClima(climasDTO);		
	
		return Optional.of(climaCidadeDTO);
	}
	
	
}
