package com.ConsumeMeter.restful.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ConsumeMeter.restful.model.ProfileAndFraction;
import com.ConsumeMeter.restful.repository.ProfileAndFractionRepository;


/**
 * 
 *  lezginaksoy
 *
 */
@Service
public class ProfileAndFractionService implements IProfileAndFraction{

	final static Logger logger = Logger.getLogger(ProfileAndFractionService.class);
    
	@Autowired
	ProfileAndFractionRepository profileAndFractionRepository;
	
	//Save
	public ProfileAndFraction save(ProfileAndFraction entity) {	
		//sum of fractions must be 1
		
		double sumOfFraction=0;
	    sumOfFraction=getTotalFractionByProfile(entity.getProfile());	    
	    sumOfFraction=sumOfFraction+entity.getFraction();	    
	    if(sumOfFraction!=1) {	    	
	    	logger.error("sum of fraction: " + sumOfFraction);
	    	 return null;
	    }
	    else{
	    	ProfileAndFraction pf=profileAndFractionRepository.save(entity);
		logger.debug("Added:: " + entity);
		return pf;
	    }		
	}
	
	//Save collection
	public int saveCollection(List<ProfileAndFraction> entity) {	
		try
		{
			if(CheckFraction(entity))
			{
				ProfileAndFraction newMr;		
				for(ProfileAndFraction mr:entity)
				{			
					 newMr=profileAndFractionRepository.save(mr);
					 logger.debug("Added:: " + newMr);
				}
				return 1;
			}
			else
			{
				logger.error("exception occured,total fraction for a prefile is different 1");
				return 0;
			}
			
			
		}
		catch(Exception ex){
			logger.error("exception occured :ex.message");
			return 0;
		}
			
			
	}

	
	//Update
	public ProfileAndFraction update(ProfileAndFraction entity) {	
		//sum of fractions must be 1
				
		double sumOfFraction=0;
		ProfileAndFraction existingprofileAndFraction= profileAndFractionRepository.findByMonthAndProfile(entity.getMonth(),entity.getProfile());
		if (existingprofileAndFraction == null) {			
			logger.error("profileAndFraction  does not exists");
			 throw new EntityExistsException("sumOfFraction must be 1..!");	
		} 
		else{
			
		    sumOfFraction=getTotalFractionByProfile(entity.getProfile());	    
		    sumOfFraction=sumOfFraction+entity.getFraction();	    
		    if(sumOfFraction!=1) {	    	
		    	logger.debug("sum of fraction: " + sumOfFraction);
		    	 throw new EntityExistsException("sumOfFraction must be 1..!");		
		    }
		    else{
		    	ProfileAndFraction pf=profileAndFractionRepository.save(entity);
			logger.debug("updated:: " + entity);
			return pf;
		    }				
		}		  	
	}

	
	
	public List<ProfileAndFraction> getAll() {		
		return profileAndFractionRepository.findAll();
	}	
	
	
	public ProfileAndFraction getByMonthAndProfile(int month,String profile) {		
		
		return profileAndFractionRepository.findByMonthAndProfile(month,profile);
	}	
	
	//Delete profile
	public boolean deleteByMonthAndProfile(ProfileAndFraction entity)
	{		
		ProfileAndFraction existingprofileAndFraction= profileAndFractionRepository.findByMonthAndProfile(entity.getMonth(),entity.getProfile());
		if (existingprofileAndFraction == null) {			
			logger.error("profileAndFraction  does not exists");
			return false;
			// throw new EntityExistsException("profileAndFraction  does not exists");	
		} 
		else
		{			
			profileAndFractionRepository.deleteByMonthAndProfile(entity.getMonth(),entity.getProfile());			
			logger.debug("deleted:: " + entity);
			return true;			
		}				
	}
		
	
	//get sum of fractions
	public double getTotalFractionByProfile(String profile) {	
		double sumofFraction=0;
		List<ProfileAndFraction> fractionList=profileAndFractionRepository.findByProfile(profile);  
		for(ProfileAndFraction pf: fractionList)
		{
			sumofFraction=sumofFraction+pf.getFraction();
		}		
		return sumofFraction;
	}

	public Boolean CheckFraction(List<ProfileAndFraction> entity)
	{
		//assume entity module 12 is 0  Each profile have data for 12 month					
	    Collections.sort(entity);
		int Count=0;
		int countProfile=entity.size()/12;	
		double fraction=0.0;
				
		//countProfile will be 12,24,36,48,.... etc.
		for(int i=0;i<countProfile;i++)
		{
			for(int j=Count;j<12;j++)
			{
				fraction +=entity.get(j).getFraction();   		
			} 
			//for each profile total fraction must be 1
			if(fraction!=1)
			{
				return false;
			}    	
			fraction=0.0;
			Count+=12;	  
		}
		
		return true;
	}
	
	
	
	@Override
	public ProfileAndFraction getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	
}
