/**
 * 
 */
package com.lam.weather.weather.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@FeignClient("weather-data-city")
public interface CityClient {

	@RequestMapping("/cities")
	String listCity();
}
