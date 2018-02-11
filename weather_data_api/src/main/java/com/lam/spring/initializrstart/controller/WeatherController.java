/**
 * 
 */
package com.lam.spring.initializrstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lam.spring.initializrstart.intf.WeatherService;
import com.lam.spring.initializrstart.vo.WeatherResponse;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping("/cityid/{id}")
	public WeatherResponse getWeatherById(@PathVariable String id) {
		return weatherService.getWeather(id);
	}
}
