/**
 * 
 */
package com.lam.spring.initializrstart.job;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.spring.initializrstart.service.WeatherDataService;
import com.lam.spring.initializrstart.vo.City;

/**
 * @author Administrator
 *
 */
public class WeatherDataSyncJob implements Job {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("重新拉去数据");
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8004/cities", String.class);
		String body = forEntity.getBody();
		JSONArray parseArray = JSON.parseArray(body);
		ObjectMapper objectMapper = new ObjectMapper();
		for (int i = 0; i < parseArray.size(); i++) {
			JSONObject jsonObject = parseArray.getJSONObject(i);
			try {
				City city = objectMapper.readValue(jsonObject.toJSONString(), City.class);
				weatherDataService.getDataByCityId(city.getCityId());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
