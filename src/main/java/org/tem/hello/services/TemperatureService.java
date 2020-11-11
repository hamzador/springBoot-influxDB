package org.tem.hello.services;


import java.util.ArrayList;
import java.util.List;


import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;
import org.tem.hello.entites.MemoryPoint;
import org.tem.hello.util.LoggingUtils;

import lombok.extern.apachecommons.CommonsLog;




@Service
@CommonsLog
public class TemperatureService {
	
	@Autowired
	InfluxDBResultMapper influxDBResultMapper;
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	

	public List<MemoryPoint> Temperaturelist() {
		
		Query query = QueryBuilder.newQuery("SELECT * FROM temperature")
		        .forDatabase("baeldug")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		log.info(LoggingUtils.getMessage(queryResult));
		
		List<MemoryPoint> rs = new InfluxDBResultMapper().toPOJO(queryResult, MemoryPoint.class);
		log.info(LoggingUtils.getMessage(rs));
		return rs;
	}
	
	
	public List<MemoryPoint> GetMemoryPoint(){
		
		
		Query query = QueryBuilder.newQuery("SELECT * FROM temperature")
		        .forDatabase("baeldung")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		
		System.out.println(queryResult.toString());
		
		List<MemoryPoint> memory = new InfluxDBResultMapper().toPOJO(queryResult, MemoryPoint.class);
		return memory;

	}
	
	
	public List<MemoryPoint> getT() {
		
		List<MemoryPoint> memory =new ArrayList<MemoryPoint>();
		final Query query = new Query("SELECT * FROM temperature", influxDBTemplate.getDatabase());
		       
		log.info(LoggingUtils.getStartMessage(query));
		
		QueryResult queryResult = influxDBTemplate.query(query);
		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		
		log.info(LoggingUtils.getMessage(queryResult));
		memory =  resultMapper.toPOJO(queryResult, MemoryPoint.class);
		
		log.info(LoggingUtils.getMessage(memory));
		log.info(LoggingUtils.getEndMessage());
		return memory;
	}
	
	
}
