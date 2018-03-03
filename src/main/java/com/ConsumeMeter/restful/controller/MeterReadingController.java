package com.ConsumeMeter.restful.controller;


import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ConsumeMeter.restful.model.MeterReading;
import com.ConsumeMeter.restful.service.IMeterReading;



/**
 * 
 *  lezginaksoy
 *
 */

@RestController
@RequestMapping("/meterreading")
public class MeterReadingController {
	

	final static Logger logger = Logger.getLogger(MeterReadingController.class);
     
	@Autowired
	IMeterReading meterReadingService;
	
	
	//For adding Collection of meter reading
	//add reading
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public int addMeterReadingCollection(@RequestBody List<MeterReading> meterReading) {
		
		int ret=meterReadingService.saveCollection(meterReading);
		logger.debug("Added:: " + ret);
		return ret;
	}
	
	
	//For single meter Read
	//add reading
	@RequestMapping(value = "/addsingle",method = RequestMethod.POST)
	public ResponseEntity<MeterReading> addMeterReading(@RequestBody MeterReading meterReading) {
		MeterReading createdmr=meterReadingService.save(meterReading);
		logger.debug("Added:: " + createdmr);
		return new ResponseEntity<MeterReading>(createdmr, HttpStatus.CREATED);
	}
	
	//for single meter read
	//update reading
	@RequestMapping(value = "/addsingle",method = RequestMethod.PUT)
	public ResponseEntity<MeterReading> updateMeterReading(@RequestBody MeterReading meterReading) {
		meterReadingService.update(meterReading);
		logger.debug("updated: " + meterReading);
		return new ResponseEntity<MeterReading>(meterReading, HttpStatus.CREATED);
	}
	
	
	//get all reading 
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public ResponseEntity<List<MeterReading>> getAllmeterReadings() {
		List<MeterReading> meterReadings = meterReadingService.getAll();
		if (meterReadings.isEmpty()) {
			logger.debug("meterreadings does not exists");
			return new ResponseEntity<List<MeterReading>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + meterReadings.size() + " meterreading");
		logger.debug(Arrays.toString(meterReadings.toArray()));
		return new ResponseEntity<List<MeterReading>>(meterReadings, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMeterReading(@PathVariable("id") Long meterid) {
		logger.debug("meterid is deleting");
		 if(meterReadingService.deleteByMeterid(meterid)) {
			 return new ResponseEntity<Void>(HttpStatus.GONE);
		 }
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	 
		 
	}
	
	
	

}
