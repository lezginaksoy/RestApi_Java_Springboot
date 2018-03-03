package com.ConsumeMeter.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ConsumeMeter.restful.model.MeterReading;


public interface MeterReadingRepository extends JpaRepository<MeterReading,Long> {
	

	   List<MeterReading> findByMonth(int month);
	   List<MeterReading> findByMeterid(long meterid);
	   
	   MeterReading findByMeteridAndMonth(long meterid,int month);
	   void deleteByMeterid(long meterid);      
	
}
