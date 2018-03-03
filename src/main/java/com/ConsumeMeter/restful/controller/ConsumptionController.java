package com.ConsumeMeter.restful.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ConsumeMeter.restful.model.MeterReading;
import com.ConsumeMeter.restful.service.MeterReadingService;


/**
 * 
 *  lezginaksoy
 *
 */
@RestController
@RequestMapping("/consume")
public class ConsumptionController {
	
	final static Logger logger = Logger.getLogger(MeterReadingController.class);
    
	@Autowired
	MeterReadingService meterReadingService;
	
	
	//get all reading 
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	public ResponseEntity<?> getConsumption(@RequestBody MeterReading meterReading) {
		double consume = meterReadingService.getConsumption(meterReading.getMeterid(),meterReading.getMonth());
		
		logger.debug("consume :" +consume);
		return new ResponseEntity<String>("consume:"+consume, HttpStatus.OK);
	}
	

}
