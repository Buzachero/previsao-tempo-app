package com.previsaotempo.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.previsaotempo.app.Constants;
import com.previsaotempo.app.controller.exception.ResourceExceptionHandler;
import com.previsaotempo.app.model.Cidade;
import com.previsaotempo.app.model.ClimaCidade;
import com.previsaotempo.app.model.Estado;
import com.previsaotempo.app.model.Pais;
import com.previsaotempo.app.repository.CidadeRepository;
import com.previsaotempo.app.repository.ClimaCidadeRepository;
import com.previsaotempo.app.repository.EstadoRepository;
import com.previsaotempo.app.repository.PaisRepository;
import com.previsaotempo.app.service.RestService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClimaCidadeControllerTest /*extends AbstractTest*/ {
	private static final String CIDADE_BASE_URL_SUFFIX = "/cidade/";
	private static Pais brasil;
	private static Estado saoPaulo;
	private static Estado minasGerais;
	private static Estado rioJaneiro;
	private static Cidade andradina;
	private static Cidade bauru;
	private static Cidade saoCarlos;
	private static Cidade beloHorizonte;
	private static Cidade niteroi;	
	private static List<ClimaCidade> climasCidade;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private MockMvc mockMvc;
	
	@Autowired
	private ClimaCidadeController climaCidadeController;
		
	@MockBean
	private RestService restService;
	
	@MockBean
	private CidadeRepository cidadeRepository;	
	@MockBean
	private EstadoRepository estadoRepository;
	@MockBean
	private PaisRepository paisRepository;
	@MockBean
	private ClimaCidadeRepository climaCidadeRepository;
	
	@BeforeClass
	public static void initialize() {		
		initializeData();
	}
	
	public static void initializeData() {		
		brasil = new Pais(null, Constants.SiglaPais.BR.name());
		saoPaulo = new Estado(null, Constants.SiglaEstados.SP.name());
		saoPaulo.setPais(brasil);
		minasGerais = new Estado(null, Constants.SiglaEstados.MG.name());
		minasGerais.setPais(brasil);
		rioJaneiro = new Estado(null, Constants.SiglaEstados.RJ.name());
		rioJaneiro.setPais(brasil);
		andradina = new Cidade(1, Constants.NomeCidades.ANDRADINA, saoPaulo);
		bauru = new Cidade(2, Constants.NomeCidades.BAURU, saoPaulo);
		saoCarlos = new Cidade(3, Constants.NomeCidades.SAO_CARLOS, saoPaulo);
		beloHorizonte = new Cidade(4, Constants.NomeCidades.BELO_HORIZONTE, minasGerais);
		niteroi = new Cidade(5, Constants.NomeCidades.NITEROI, rioJaneiro);
		
		ClimaCidade climaCidadeC1 = new ClimaCidade(andradina, LocalDate.now(), 70, 5, 20, 30);				
		ClimaCidade climaCidadeC1_1 = new ClimaCidade(andradina, LocalDate.now().plusDays(1), 80, 6, 21, 38);		
		andradina.getClimasCidade().addAll(Arrays.asList(climaCidadeC1, climaCidadeC1_1));
					
		
		ClimaCidade climaCidadeC2 = new ClimaCidade(bauru, LocalDate.now(), 50, 10, 25, 28);		
		bauru.getClimasCidade().addAll(Arrays.asList(climaCidadeC2));
				
		ClimaCidade climaCidadeC3 = new ClimaCidade(saoCarlos, LocalDate.now(), 30, 20, 18, 22);		
		ClimaCidade climaCidadeC3_1 = new ClimaCidade(saoCarlos, LocalDate.now().plusDays(1), 40, 15, 19, 24);				
		ClimaCidade climaCidadeC3_2 = new ClimaCidade(saoCarlos, LocalDate.now().plusDays(2), 60, 15, 20, 25);		
		saoCarlos.getClimasCidade().addAll(Arrays.asList(climaCidadeC3, climaCidadeC3_1, climaCidadeC3_2));	
		
		ClimaCidade climaCidadeC4 = new ClimaCidade(beloHorizonte, LocalDate.now(), 45, 40, 15, 20);		
		beloHorizonte.getClimasCidade().addAll(Arrays.asList(climaCidadeC4));
		
		ClimaCidade climaCidadeC5 = new ClimaCidade(niteroi, LocalDate.now(), 3, 90, 24, 28);		
		niteroi.getClimasCidade().addAll(Arrays.asList(climaCidadeC5));
		
		climasCidade = new ArrayList<>();
		
		climasCidade.addAll(Arrays.asList(climaCidadeC1, climaCidadeC1_1, climaCidadeC2, climaCidadeC3, climaCidadeC3_1, climaCidadeC3_2, climaCidadeC4, climaCidadeC5));
	
	}

	@Before
	public void initializeMocks() {			
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.climaCidadeController)
										.setControllerAdvice(new ResourceExceptionHandler())
										.build();
		Mockito.when(paisRepository.findBySigla(anyString())).thenReturn(brasil);
		Mockito.doReturn(null).when(paisRepository).save(any());
		Mockito.doReturn(null).when(estadoRepository).save(any());
		Mockito.doReturn(null).when(cidadeRepository).save(any());
		Mockito.doReturn(null).when(climaCidadeRepository).saveAll(any());
	}	


	@Test
	public void test_getClimaByCidadeId_cidadeId_1_shouldReturn_climacidade_Andradina_SP_BR() throws Exception {	
		final int cidadeId = 1;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;	
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(climasCidade.subList(0, 2));		
		Mockito.when(estadoRepository.findBySigla(anyString())).thenReturn(saoPaulo);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cidade").value(Constants.NomeCidades.ANDRADINA))
				.andExpect(jsonPath("$.estado").value(Constants.SiglaEstados.SP.name()))
				.andExpect(jsonPath("$.pais").value(Constants.SiglaPais.BR.name()))
				.andExpect(jsonPath("$.clima").isArray())
				.andExpect(jsonPath("$.clima", hasSize(2)))
				.andExpect(jsonPath("$.clima[0].dataPrevisao").value(formatter.format(LocalDate.now().plusDays(1))))
				.andExpect(jsonPath("$.clima[0].probabilidadeChuva").value(80))
				.andExpect(jsonPath("$.clima[0].precipitacao").value(6))				
				.andExpect(jsonPath("$.clima[0].temperaturaMinima").value(21))
				.andExpect(jsonPath("$.clima[0].temperaturaMaxima").value(38))
				.andExpect(jsonPath("$.clima[1].dataPrevisao").value(formatter.format(LocalDate.now())))
				.andExpect(jsonPath("$.clima[1].probabilidadeChuva").value(70))
				.andExpect(jsonPath("$.clima[1].precipitacao").value(5))				
				.andExpect(jsonPath("$.clima[1].temperaturaMinima").value(20))
				.andExpect(jsonPath("$.clima[1].temperaturaMaxima").value(30));				
	}
	
	@Test
	public void test_getClimaByCidadeId_cidadeId_2_shouldReturn_climacidade_Bauru_SP_BR() throws Exception {	
		final int cidadeId = 2;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;		
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(climasCidade.subList(2, 3));		
		Mockito.when(estadoRepository.findBySigla(anyString())).thenReturn(saoPaulo);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cidade").value(Constants.NomeCidades.BAURU))
				.andExpect(jsonPath("$.estado").value(Constants.SiglaEstados.SP.name()))
				.andExpect(jsonPath("$.pais").value(Constants.SiglaPais.BR.name()))
				.andExpect(jsonPath("$.clima").isArray())
				.andExpect(jsonPath("$.clima", hasSize(1)))
				.andExpect(jsonPath("$.clima[0].dataPrevisao").value(formatter.format(LocalDate.now())))
				.andExpect(jsonPath("$.clima[0].probabilidadeChuva").value(50))
				.andExpect(jsonPath("$.clima[0].precipitacao").value(10))				
				.andExpect(jsonPath("$.clima[0].temperaturaMinima").value(25))
				.andExpect(jsonPath("$.clima[0].temperaturaMaxima").value(28));				
	}
	
	@Test
	public void test_getClimaByCidadeId_cidadeId_3_shouldReturn_climacidade_SaoCarlos_SP_BR() throws Exception {	
		final int cidadeId = 3;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;		
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(climasCidade.subList(3, 6));		
		Mockito.when(estadoRepository.findBySigla(anyString())).thenReturn(saoPaulo);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cidade").value(Constants.NomeCidades.SAO_CARLOS))
				.andExpect(jsonPath("$.estado").value(Constants.SiglaEstados.SP.name()))
				.andExpect(jsonPath("$.pais").value(Constants.SiglaPais.BR.name()))
				.andExpect(jsonPath("$.clima").isArray())
				.andExpect(jsonPath("$.clima", hasSize(3)))
				.andExpect(jsonPath("$.clima[0].dataPrevisao").value(formatter.format(LocalDate.now().plusDays(2))))
				.andExpect(jsonPath("$.clima[0].probabilidadeChuva").value(60))
				.andExpect(jsonPath("$.clima[0].precipitacao").value(15))				
				.andExpect(jsonPath("$.clima[0].temperaturaMinima").value(20))
				.andExpect(jsonPath("$.clima[0].temperaturaMaxima").value(25))
				.andExpect(jsonPath("$.clima[1].dataPrevisao").value(formatter.format(LocalDate.now().plusDays(1))))
				.andExpect(jsonPath("$.clima[1].probabilidadeChuva").value(40))
				.andExpect(jsonPath("$.clima[1].precipitacao").value(15))				
				.andExpect(jsonPath("$.clima[1].temperaturaMinima").value(19))
				.andExpect(jsonPath("$.clima[1].temperaturaMaxima").value(24))
				.andExpect(jsonPath("$.clima[2].dataPrevisao").value(formatter.format(LocalDate.now())))
				.andExpect(jsonPath("$.clima[2].probabilidadeChuva").value(30))
				.andExpect(jsonPath("$.clima[2].precipitacao").value(20))				
				.andExpect(jsonPath("$.clima[2].temperaturaMinima").value(18))
				.andExpect(jsonPath("$.clima[2].temperaturaMaxima").value(22));				
	}
	
	@Test
	public void test_getClimaByCidadeId_cidadeId_4_shouldReturn_climacidade_BeloHorizonte_MG_BR() throws Exception {	
		final int cidadeId = 4;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;		
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(climasCidade.subList(6, 7));		
		Mockito.when(estadoRepository.findBySigla(anyString())).thenReturn(minasGerais);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cidade").value(Constants.NomeCidades.BELO_HORIZONTE))
				.andExpect(jsonPath("$.estado").value(Constants.SiglaEstados.MG.name()))
				.andExpect(jsonPath("$.pais").value(Constants.SiglaPais.BR.name()))
				.andExpect(jsonPath("$.clima").isArray())
				.andExpect(jsonPath("$.clima", hasSize(1)))
				.andExpect(jsonPath("$.clima[0].dataPrevisao").value(formatter.format(LocalDate.now())))
				.andExpect(jsonPath("$.clima[0].probabilidadeChuva").value(45))
				.andExpect(jsonPath("$.clima[0].precipitacao").value(40))				
				.andExpect(jsonPath("$.clima[0].temperaturaMinima").value(15))
				.andExpect(jsonPath("$.clima[0].temperaturaMaxima").value(20));				
	}
	
	@Test
	public void test_getClimaByCidadeId_cidadeId_5_shouldReturn_climacidade_Niteroi_RJ_BR() throws Exception {	
		final int cidadeId = 5;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;		
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(climasCidade.subList(7, 8));		
		Mockito.when(estadoRepository.findBySigla(anyString())).thenReturn(saoPaulo);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.cidade").value(Constants.NomeCidades.NITEROI))
				.andExpect(jsonPath("$.estado").value(Constants.SiglaEstados.RJ.name()))
				.andExpect(jsonPath("$.pais").value(Constants.SiglaPais.BR.name()))
				.andExpect(jsonPath("$.clima").isArray())
				.andExpect(jsonPath("$.clima", hasSize(1)))
				.andExpect(jsonPath("$.clima[0].dataPrevisao").value(formatter.format(LocalDate.now())))
				.andExpect(jsonPath("$.clima[0].probabilidadeChuva").value(3))
				.andExpect(jsonPath("$.clima[0].precipitacao").value(90))				
				.andExpect(jsonPath("$.clima[0].temperaturaMinima").value(24))
				.andExpect(jsonPath("$.clima[0].temperaturaMaxima").value(28));				
	}
	
	@Test
	public void test_getClimaByCidadeId_unknown_cidade_shouldReturn_not_found() throws Exception {
		final int cidadeId = 99999;
		final String uri = CIDADE_BASE_URL_SUFFIX + cidadeId;		
		List<ClimaCidade> emptyList = new ArrayList<>();
		
		Mockito.when(restService.getClimaByCidadeId(cidadeId)).thenReturn(emptyList);
		
		mockMvc.perform(get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath("$.error").value("Objeto não encontrado"));
	}

}
