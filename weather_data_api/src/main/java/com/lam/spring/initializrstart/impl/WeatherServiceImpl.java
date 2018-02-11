package com.lam.spring.initializrstart.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.spring.initializrstart.intf.WeatherService;
import com.lam.spring.initializrstart.vo.WeatherResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public WeatherResponse getWeather(String id) {
		String uri = WEATHER_URI + "citykey=" + id;
		Boolean hasKey = stringRedisTemplate.hasKey(uri);
		ObjectMapper mapper = new ObjectMapper();
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		WeatherResponse entity = null;
		if(hasKey) {
			try {
				String string = opsForValue.get(uri);
				System.out.println(string);
				entity = mapper.readValue(string, WeatherResponse.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
}
