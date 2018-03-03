package com.ConsumeMeter.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 *  Lezgin Aksoy
 *
 */

@Entity
@Table(name = "MeterReading")
public class MeterReading implements Comparable<MeterReading> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	
	@NotNull(message = "meterid  is required" )
	@Column(name = "meterid")
    private Long meterid;
	
	
	@NotNull(message = "profile  is required" )
	@Column(name = "profile", length = 1)
	private String profile ;
	
    //month ;month=2-->FEB,3-->MARCH.....	
	@NotNull(message = "month  is required" )
	@Column(name = "month")
	private int  month;
	
	
	@NotNull(message = "meterreading  is required" )
	@Column(name = "meterreading")
	private int meterreading;

	
	
	
	public MeterReading(Long meterid, String profile, int month, int meterReading) {		
		this.meterid = meterid;
		this.profile = profile;
		this.month = month;
		this.meterreading = meterReading;
	}
	
	public MeterReading(){		
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public long getMeterid() {
		return meterid;
	}

	public void setMeterid(Long meterid) {
		this.meterid = meterid;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMeterReading() {
		return meterreading;
	}

	public void setMeterReading(int meterReading) {
		this.meterreading = meterReading;
	}

	@Override
	public int compareTo(MeterReading compareMeterReading) {		
		int compareMonth = ((MeterReading) compareMeterReading).getMonth();
		//ascending order
		return this.month - compareMonth;		
		 
	}
	
	
	

	

}
