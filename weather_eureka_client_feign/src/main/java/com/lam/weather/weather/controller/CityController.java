/**
 * 
 */
package com.lam.weather.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lam.weather.weather.service.CityClient;

/**
 * @author Administrator
 *
 */
@RestController
public class CityController {
	
	@Autowired
	private CityClient cityClient;
	
	@RequestMapping("/cities")
	public String listCity() {
		//通过feign客户端来查询
		return cityClient.listCity();
	}
}
