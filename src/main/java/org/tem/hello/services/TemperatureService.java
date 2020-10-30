package org.tem.hello.services;


import java.util.List;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;
import org.tem.hello.domain.MemoryPoint;




@Service
public class TemperatureService {
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	

	public List<MemoryPoint> Temperaturelist() {
		Query query = QueryBuilder.newQuery("SELECT * FROM memory")
		        .forDatabase("iot")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		
		
		return new InfluxDBResultMapper().toPOJO(queryResult, MemoryPoint.class);
	}
	
	
	public List<MemoryPoint> GetMemoryPoint(){
		
		
		Query query = QueryBuilder.newQuery("SELECT * FROM memory")
		        .forDatabase("iot")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		
		System.out.println(queryResult.toString());
		
		List<MemoryPoint> memory = new InfluxDBResultMapper().toPOJO(queryResult, MemoryPoint.class);
		return memory;

	}
	

	
	
}
