package org.tem.hello.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tem.hello.entites.MemoryPoint;
import org.tem.hello.services.TemperatureService;
import org.tem.hello.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;


@RestController
@RequestMapping( "/source")
//@CrossOrigin("*")
@CommonsLog
public class TemperatureController {
	//@Value("${temp.url}")
	//private String url;
	//public  List<Temperature> sendMessage(){
	//	List <Temperature> temperature =null;
	//	return temperature;
	//}
	
	
	@Autowired
	private TemperatureService temperatureService;


	@RequestMapping("/{userId}")
	public  List<MemoryPoint> TemperatureList(@PathVariable("userId")String userId){
		 log.info(LoggingUtils.getStartMessage());
		 List<MemoryPoint> rs=temperatureService.getT();
		 log.info(LoggingUtils.getEndMessage());
		 return rs;
		
	}
	
	@RequestMapping("/userId")
	public  String TemperatureList(){
		 
		return "erghfgjghkhjljk";
		
	}
	
	
	


}
