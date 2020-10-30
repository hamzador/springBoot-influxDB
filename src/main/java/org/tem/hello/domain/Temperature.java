package org.tem.hello.domain;

import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;



 
//@Data				//getter & setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity

@Measurement(name = "temperature")
public class Temperature {
	
	@Column(name = "id")
	//@javax.persistence.Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	@Column(name = "keyT")
	private String keyT;
	@Column(name = "valueT")
	private double valueT;
	@Column(name = "dateT")
	private Date dateT;
	
	
	public Temperature(Long id, String keyT, double valueT, Date dateT) {
		super();
		Id = id;
		this.keyT = keyT;
		this.valueT = valueT;
		this.dateT = dateT;
	}


	public Temperature() {
		super();
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getKeyT() {
		return keyT;
	}


	public void setKeyT(String keyT) {
		this.keyT = keyT;
	}


	public double getValueT() {
		return valueT;
	}


	public void setValueT(double valueT) {
		this.valueT = valueT;
	}


	public Date getDateT() {
		return dateT;
	}


	public void setDateT(Date dateT) {
		this.dateT = dateT;
	}
	
	
	
	
	
	
	
	
	
	
	

}
