package com.lam.spring.initializrstart.impl;

import java.util.List;

import com.lam.spring.initializrstart.vo.City;

/**
 * City Data Service.
 * 
 * @since 1.0.0 2017年11月23日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity();
}
