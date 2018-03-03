package com.ConsumeMeter.restful.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ConsumeMeter.restful.model.ProfileAndFraction;
import com.ConsumeMeter.restful.service.ProfileAndFractionService;


/**
 * 
 *  lezginaksoy
 *
 */

@RestController
@RequestMapping("/fractions")
public class ProfileAndFractionController {


	final static Logger logger = Logger.getLogger(ProfileAndFractionController.class);
    
	@Autowired
	ProfileAndFractionService profileAndFractionService;
	
	//For adding Collection of profile
	//add reading
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public int addMeterReadingCollection(@RequestBody List<ProfileAndFraction> profile) {
		
		int ret=profileAndFractionService.saveCollection(profile);
		logger.debug("Added:: " + ret);
		return ret;
	}
	
	@RequestMapping(value = "/addsingle",method = RequestMethod.POST)
	public ResponseEntity<ProfileAndFraction> addProfileAndFraction(@RequestBody ProfileAndFraction profileAndFraction) {
		profileAndFractionService.save(profileAndFraction);
		logger.debug("Added:: " + profileAndFraction);
		return new ResponseEntity<ProfileAndFraction>(profileAndFraction, HttpStatus.CREATED);
	}
		

	@RequestMapping(value = "/addsingle",method = RequestMethod.PUT)
	public ResponseEntity<ProfileAndFraction> updateProfileAndFraction(@RequestBody ProfileAndFraction profileAndFraction) {
		profileAndFractionService.update(profileAndFraction);
		logger.debug("updated:: " + profileAndFraction);
		return new ResponseEntity<ProfileAndFraction>(profileAndFraction, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public ResponseEntity<List<ProfileAndFraction>> getAllProfileAndFractions() {
		List<ProfileAndFraction> profileAndFraction = profileAndFractionService.getAll();
		if (profileAndFraction.isEmpty()) {
			logger.debug("profileAndFraction does not exists");
			return new ResponseEntity<List<ProfileAndFraction>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + profileAndFraction.size() + " ProfileAndFraction");
		logger.debug(Arrays.toString(profileAndFraction.toArray()));
		return new ResponseEntity<List<ProfileAndFraction>>(profileAndFraction, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProfileAndFraction(@RequestBody ProfileAndFraction profileAndFraction) {
		logger.debug("meterid is deleting");
		if(profileAndFractionService.deleteByMonthAndProfile(profileAndFraction)) {
		 return new ResponseEntity<Void>(HttpStatus.GONE);
	     }
	     return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	 
		
	}
	
	
	
	
	
}
