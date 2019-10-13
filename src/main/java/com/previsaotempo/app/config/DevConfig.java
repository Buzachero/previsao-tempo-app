package com.previsaotempo.app.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.previsaotempo.app.service.DBService;
import com.previsaotempo.app.service.RestService;


@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private RestService restService;
	
	
	

}
