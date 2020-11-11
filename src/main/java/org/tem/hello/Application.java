package org.tem.hello;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBTemplate;








@SpringBootApplication
public class Application implements CommandLineRunner{
	
	
	private InfluxDB influxDB ;
	
	//@Value("${database.url}")
	//private String UrlDB;
	
	
	
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	
	@Autowired
	 InfluxDBConnectionFactory connectionFactory;
	

	public static void main(String[] args) {
        
		SpringApplication.run(Application.class, args);
        
    }
    
	
	@Override
	public void run(String... args) throws Exception {
		
		//Creating a Connection
		// influxDB = InfluxDBFactory.connect(UrlDB, "admin", "admin");
		
		// influxDB=  connectionFactory.getConnection();

		//Creating a Database
		//influxDB.createDatabase("iot");
		//influxDB.createRetentionPolicy("defaultPolicy", "baeldung", "30d", 1, true);
		
		//Setting a Logging Level
		//influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
		
		
		//Adding and Retrieving Data
		//Writing Batches
		 
		Map<String,Object> map =new HashMap<>();
		 map.put("nameSensor", "sensor2");
		 map.put("ValueTemperature", 8.1);
		 map.put("nameSensor", "sensor2");
		 
		 Date date=new Date();
		 
		 influxDBTemplate.createDatabase();
		 
		 final Point p = Point.measurement("temperature")
		   //.time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
		   .tag("nameSensor", (String)map.get("nameSensor"))
		   .addField("date", date.getTime())
		   .addField("ValueTemperature",(double)map.get("ValueTemperature"))
		   .build();
		 influxDBTemplate.write(p);
		 
		 
		 
		 
		/*
		 * BatchPoints batchPoints = BatchPoints
		 
				  .database("baeldung")
				  .retentionPolicy("defaultPolicy")
				  .build();
		
	
		
		Point point1 = Point.measurement("temperature")
				.addField("date",date.getTime() )
				  .addField("nameSensor",(String)map.get("nameSensor"))
				  .addField("ValueTemperature",(double)map.get("ValueTemperature"))
				  .build();
		
		
		 Point point2 = Point.measurement("temperature")
				.time(date.getTime(), TimeUnit.MILLISECONDS)
				  .addField("nameSensor", "sensor1") 
				  .addField("ValueTemperature",(double) 6.6)
				  .build();
				
		
		batchPoints.point(point1);
		//batchPoints.point(point2);
		//influxDBTemplate.write(point1);
		influxDB.write(batchPoints);*/
		
		 
	
		//influxDB.deleteDatabase("baeldung");
		
	}
	

}
