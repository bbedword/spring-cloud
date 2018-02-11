package com.lam.spring.initializrstart.impl;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lam.spring.initializrstart.vo.City;
import com.lam.spring.initializrstart.vo.WeatherResponse;

/**
 * City Data Service.
 * 
 * @since 1.0.0 2017年11月23日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@FeignClient("weather-eureka-client-zuul")
public interface DataClient {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/city/cities")
	List<City> listCity();

	@RequestMapping("/data/weather/cityid/{id}")
	WeatherResponse getDataByCityId(@PathVariable("id") String id);
}
