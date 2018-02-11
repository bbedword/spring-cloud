/**
 * 
 */
package com.lam.spring.initializrstart.intf;

import com.lam.spring.initializrstart.vo.WeatherResponse;

/**
 * @author Administrator
 *
 */
public interface WeatherService {

	WeatherResponse getWeather(String id);
}
