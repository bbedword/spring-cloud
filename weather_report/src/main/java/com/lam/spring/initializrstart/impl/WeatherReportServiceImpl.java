package com.lam.spring.initializrstart.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.spring.initializrstart.vo.Weather;
import com.lam.spring.initializrstart.vo.WeatherResponse;

/**
 * Weather Report Service.
 * 
 * @since 1.0.0 2017年11月24日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
	@Autowired
	private RestTemplate  restTemplate;
	
	@Override
	public Weather getDataByCityId(String cityId) {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8001/weather/cityid/"+cityId, String.class);
		String body = forEntity.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(body, WeatherResponse.class).getData();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
