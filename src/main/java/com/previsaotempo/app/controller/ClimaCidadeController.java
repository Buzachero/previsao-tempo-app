package com.previsaotempo.app.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.previsaotempo.app.dto.ClimaCidadeDTO;
import com.previsaotempo.app.model.ClimaCidade;
import com.previsaotempo.app.service.ClimaCidadeService;

@RestController
@RequestMapping(value="/cidade")
public class ClimaCidadeController {
	
	@Autowired
	private ClimaCidadeService climaCidadeService;

	@GetMapping(value = "/{cidadeId}")
	public ResponseEntity<ClimaCidadeDTO> getClimaByCidadeId(@PathVariable Integer cidadeId) throws IOException {	
		ClimaCidadeDTO climaCidadeDTO = climaCidadeService.findByCidadeId(cidadeId);
		
		System.out.println("PASSEI NO CONTROLLER!");
		
		if(climaCidadeDTO != null) {
			System.out.println("climaCidadeDTO = " + climaCidadeDTO.toString());
		}
		
		return ResponseEntity.ok().body(climaCidadeDTO);
	}
}
	
