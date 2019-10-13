package com.previsaotempo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.previsaotempo.app.dto.AnaliseClimaticaDTO;
import com.previsaotempo.app.service.AnaliseClimaticaService;

@RestController
@RequestMapping(value="/analise")
public class AnaliseClimaticaController {
	
	@Autowired
	private AnaliseClimaticaService analiseClimaticaService;

	@GetMapping()
	public ResponseEntity<AnaliseClimaticaDTO> getClimaAnalysis(
			@RequestParam(value="data_inicial", required=true) String dataInicial, 
			@RequestParam(value="data_final", required=true) String dataFinal) {
		
		AnaliseClimaticaDTO analiseClimatica = analiseClimaticaService.findClimasByDateRange(dataInicial, dataFinal);
				
		return ResponseEntity.ok().body(analiseClimatica);
	}
	
	
	
}
	
