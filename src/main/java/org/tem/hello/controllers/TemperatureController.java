package org.tem.hello.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tem.hello.domain.MemoryPoint;
import org.tem.hello.services.TemperatureService;


@RestController
@RequestMapping(value = "/api/dashboard/")
@CrossOrigin("*")
public class TemperatureController {
	
	//public  List<Temperature> sendMessage(){
	//	List <Temperature> temperature =null;
	//	return temperature;
	//}
	
	
	@Autowired
	private TemperatureService temperatureService;


	@RequestMapping("/list")
	public  List<MemoryPoint> TemperatureList(){
		 
		 
		return temperatureService.GetMemoryPoint();
		
	}
	


}
