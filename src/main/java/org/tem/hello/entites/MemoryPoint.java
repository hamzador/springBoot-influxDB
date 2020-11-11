package org.tem.hello.entites;





import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import lombok.Data;


@Data
@Measurement(name = "temperature")
public class MemoryPoint {

    
    
 
   
    @Column(name = "nameSensor", tag=true)
    private String nameSensor;
 
    @Column(name = "ValueTemperature")
    private double ValueTemperature;
   
    @Column(name = "date")
	private Long date;

	
    
    
    public MemoryPoint(String nameSensor, double valueTemperature, Long date) {
		super();
		this.nameSensor = nameSensor;
		ValueTemperature = valueTemperature;
		this.date = date;
	}




	public MemoryPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
   
	
   
	

    
    
    
    
}