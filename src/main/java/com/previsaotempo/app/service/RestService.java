package com.previsaotempo.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previsaotempo.app.model.Cidade;
import com.previsaotempo.app.model.ClimaCidade;
import com.previsaotempo.app.model.Estado;
import com.previsaotempo.app.model.Pais;

@Service
public class RestService {
	private RestTemplate restTemplate = new RestTemplate();
	private final String CIDADE_ID_TOKEN = "<ID_DA_CIDADE>";
	private final String TOKEN = "<TOKEN>";
	private final ObjectMapper mapper = new ObjectMapper();
	private HttpHeaders headers = new HttpHeaders();
	private HttpEntity<String> entity;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final String USER_AGENT = "user-agent";
	private final String BROWSER_SPEC = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
		
	@Value("${previsaotempo.url}")
	private String previsaoTempoURL;	
	@Value("${previsaotempo.token}")
	private String token;
	
	public List<ClimaCidade> getClimaByCidadeId(Integer cidadeId) throws IOException {		
		String apiURL = previsaoTempoURL.replace(CIDADE_ID_TOKEN, String.valueOf(cidadeId));		
		apiURL = apiURL.replace(TOKEN, token);        
        
        System.out.println("apiURL = " + apiURL);
        
        if(entity == null) {
        	headers.add(USER_AGENT, BROWSER_SPEC);
        	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        	entity = new HttpEntity<String>("parameters", headers);
        }
        
        ResponseEntity<String> result = restTemplate.exchange(apiURL, HttpMethod.GET,entity, String.class);	

        System.out.println("Body response: " + result.getBody());
        
		return parseJsonToObject(result.getBody());
	}
	
	public List<ClimaCidade> parseJsonToObject(String json) throws IOException {	
		List<ClimaCidade> climasCidade = new ArrayList<>();
		
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
		Pais pais = new Pais();	
		
		JsonNode root = mapper.readTree(json);
		
        // Get id
        Integer cidadeId = root.path("id").asInt();
        System.out.println("cidadeId : " + cidadeId);
        cidade.setId(cidadeId);        
        
        String name = root.path("name").asText();
        System.out.println("name : " + name);
        cidade.setNome(name);        
        
        String state = root.path("state").asText();
        System.out.println("state : " + state);
        if(state != null) {
        	state = state.trim();        	
        } 
        estado.setSigla(state);        
        
        String country = root.path("country").asText();
        System.out.println("country : " + country);
        
        if(country != null) {
        	country = country.trim();        	
        }         
        
        pais.setSigla(country);
        estado.setPais(pais);
        cidade.setEstado(estado);        
        
        JsonNode datasNode = root.path("data");
        if (!datasNode.isMissingNode()) { 
        	for (JsonNode dataNode : datasNode) {
        		System.out.println("###############################");
        		 String date_br = dataNode.path("date_br").asText();
                 System.out.println("date_br : " + date_br);
                
                 JsonNode rainNode = dataNode.path("rain");
                 Integer probability = rainNode.path("probability").asInt();
                 System.out.println("probability : " + probability);
                 
                 Integer precipitation = rainNode.path("precipitation").asInt();
                 System.out.println("precipitation : " + precipitation);
                 
                 JsonNode temperatureNode = dataNode.path("temperature");
                 Integer minTemp = temperatureNode.path("min").asInt();
                 System.out.println("minTemp : " + minTemp);
                 
                 Integer maxTemp = temperatureNode.path("max").asInt();
                 System.out.println("maxTemp : " + maxTemp);
                 
                 if(probability == 0
                		 && precipitation == 0
                		 && minTemp == 0
                		 && maxTemp == 0) {
                	 continue;
                 }
                 
                 climasCidade.add(new ClimaCidade(cidade, LocalDate.parse(date_br, formatter), probability, precipitation, minTemp, maxTemp));
                 
        	}
            
        }	
        
        return climasCidade;
	}
}
