/**
 * 
 */
package com.lam.spring.initializrstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lam.spring.initializrstart.impl.DataClient;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/report")
public class WeatherController {

	@Autowired
	private DataClient dataClient;
	
	@RequestMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
		model.addAttribute("title", "老卫的天气预报");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", dataClient.listCity());
		model.addAttribute("report", dataClient.getDataByCityId(cityId).getData());
		return new ModelAndView("weather/report", "reportModel", model);
	}
}
