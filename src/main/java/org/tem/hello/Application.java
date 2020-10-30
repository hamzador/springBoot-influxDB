package org.tem.hello;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tem.hello.domain.MemoryPoint;


//import org.springframework.data.influxdb.InfluxDBTemplate;
@RestController
@SpringBootApplication
public class Application implements CommandLineRunner{
  //private InfluxDBTemplate influxDBTemplate;	
	private InfluxDB influxDB ;
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	public static void main(String[] args) {
        
		SpringApplication.run(Application.class, args);
        
    }
    
	
	@Override
	public void run(String... args) throws Exception {
		
		//Creating a Connection
		 influxDB = InfluxDBFactory.connect("http://localhost:8086/", "admin", "admin");
		
	

		//Creating a Database
		influxDB.createDatabase("iot");
		influxDB.createRetentionPolicy("defaultPolicy", "iot", "30d", 1, true);
		
		//Setting a Logging Level
		influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
		
		
		//Adding and Retrieving Data
		//Writing Batches
		BatchPoints batchPoints = BatchPoints
				  .database("iot")
				  .retentionPolicy("defaultPolicy")
				  .build();
				 
		Point point1 = Point.measurement("memory")
				.time(System.currentTimeMillis(), TimeUnit.MICROSECONDS)
				  .addField("name", "server2") 
				  .addField("free", 4743656L)
				  .addField("used", 1015096L) 
				  .addField("buffer", 1010467L)
				  .build();
				 
		Point point2 = Point.measurement("memory")
				  .time(System.currentTimeMillis() - 100, TimeUnit.MICROSECONDS)
				  .addField("name", "server2")
				  .addField("free", 4743696L)
				  .addField("used", 1016096L)
				  .addField("buffer", 1008467L)
				  .build();
				 
		batchPoints.point(point1);
		batchPoints.point(point2);
		//influxDB.write(batchPoints);
		
		
	}
	
	@RequestMapping("/t")
	public List<MemoryPoint> getT() {
		List<MemoryPoint> memory;
		Query query = QueryBuilder.newQuery("SELECT * FROM memory")
		        .forDatabase("iot")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		
		System.out.println(queryResult.toString());
		
		
		memory = new InfluxDBResultMapper().toPOJO(queryResult, MemoryPoint.class);
		return memory;
	}
}
