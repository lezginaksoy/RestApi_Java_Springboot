package com.ConsumeMeter.restful.service;

import java.util.List;



import com.ConsumeMeter.restful.model.MeterReading;

/**
 * 
 *  LezginAksoy
 *
 */
public interface IMeterReading extends CRUDService<MeterReading> {	

	List<MeterReading> getByMeterid(long meterid);
	MeterReading update(MeterReading entity);
	Boolean deleteByMeterid(long meterid);
	MeterReading getByMeteridAndMonth(MeterReading entity);
	double getConsumption(long meterid,int month);
	int saveCollection(List<MeterReading> entity);
} 
