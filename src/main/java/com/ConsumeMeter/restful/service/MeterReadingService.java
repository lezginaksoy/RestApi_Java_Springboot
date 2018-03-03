package com.ConsumeMeter.restful.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ConsumeMeter.restful.model.MeterReading;
import com.ConsumeMeter.restful.model.ProfileAndFraction;
import com.ConsumeMeter.restful.repository.MeterReadingRepository;
import com.ConsumeMeter.restful.repository.ProfileAndFractionRepository;



/**
 *  LezginAksoy
 */

@Service
public class MeterReadingService implements IMeterReading{


	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	@Autowired
	private MeterReadingRepository meterReadingRepository;
	
	@Autowired
	ProfileAndFractionRepository profileAndFractionRepository;
	

	//for single reading
	//Save reading
	@Override
	public MeterReading save(MeterReading entity) 
	{	
		int PreviousMonth=0;	
		MeterReading newMr;
		MeterReading Premr=meterReadingRepository.findByMeteridAndMonth(entity.getMeterid(),entity.getMonth());
		  if(Premr==null)
		  {//meterid and month are unique together,
			  
			  if(entity.getMonth()==1)
				{//if month is january no need to check anything..!(all data are reseted before)
				      newMr=meterReadingRepository.save(entity);
					  logger.debug("Added:: " + entity);
					  return newMr;			
				}
			  else
			  {//it is different than 1 month(jan),so chck previous month
				  PreviousMonth=entity.getMonth()-1;				  
				  Premr=meterReadingRepository.findByMeteridAndMonth(entity.getMeterid(),PreviousMonth);
				  if(Premr!=null)
				  {//check previous month
					  if(entity.getMeterReading() < Premr.getMeterReading()) 
					  {
						  logger.error("previous reading: " + Premr.getMeterReading());
						  return null;
					    	// throw new EntityExistsException("previous readig must be lower than current reading!");		
					  }
					  else
					  {
						    newMr=meterReadingRepository.save(entity);
							logger.debug("Added:: " + entity);
							return newMr;	
					  }
				  }
				  else
				  {		
					  logger.error("previous readig must not be null or empty, ");					 
					  return null;
				   	// throw new EntityExistsException("previous readig must not be null or empty");		
				  }
				  
			  }
			  
		  }	  
		  else
		  {
			  logger.error("there alredy this meterredding for that month: ");
			  //throw new EntityExistsException("previous readig must not be null or empty");
			  return null;
		  }
		
	
	
	}

		
	//for single meter reading
	//update reading
	public MeterReading update(MeterReading entity) {	
		int PreviousMonth=0;			
		MeterReading mr=new MeterReading();	
		MeterReading existingMr=meterReadingRepository.findByMeteridAndMonth(entity.getMeterid(),entity.getMonth());		
		if (existingMr == null) {	
			//no update 
			logger.error("MeterReading  does not exists");
			 return null;	
		} 
		else
		{			
			if(entity.getMonth()==1)
			{//if month is january no need to check anything..!(all data are reseted before)
				existingMr.setMeterReading(entity.getMeterReading());
			    	
			    	MeterReading updatedMr=meterReadingRepository.save(existingMr);
					logger.debug("Added:: " + updatedMr);
					return updatedMr;			
			}
			else		
			{     //check previous meterread and compare with current read.!
				  PreviousMonth=entity.getMonth()-1;		
				  MeterReading previousMr =meterReadingRepository.findByMeteridAndMonth(entity.getMeterid(),PreviousMonth);
				  if(previousMr!=null)
				  {					  
					  if(entity.getMeterReading()<previousMr.getMeterReading()) 
					  {
						  logger.error("previous readig must be lower than current reading reading: ");
						  return null;
					    	// throw new EntityExistsException("previous readig must be lower than current reading!");		
					  }
					  else 
					  {
						  mr=meterReadingRepository.save(entity);
							logger.debug("updated:: " + entity);
							return mr;	
					  }
				  }
				  else
				  {
					  logger.error("previous readig must not be null : ");
					  return null;
				  }				  
			 }				
		}	
		
	}

	
   //get all reading
	public List<MeterReading> getAll()
	{	
		return meterReadingRepository.findAll();		
	}

	
	//get by meterid
	@Override
	public List<MeterReading> getByMeterid(long meterid)
	{	
		return meterReadingRepository.findByMeterid(meterid);
	}
	
	//get by meterid and month
	public MeterReading getByMeteridAndMonth(MeterReading mr)
	{	
		return meterReadingRepository.findByMeteridAndMonth(mr.getMeterid(),mr.getMonth());
	}
	
	//Delete reading
	public Boolean deleteByMeterid(long meterid)
	{
		List<MeterReading> existingmeterreading= meterReadingRepository.findByMeterid(meterid);
		if (existingmeterreading == null) {			
			logger.error("existingmeterreading  does not exists");
			return false;
			 //throw new EntityExistsException("existingmeterreading  does not exists");	
		} 
		else
		{			
			meterReadingRepository.deleteByMeterid(meterid);			
			logger.debug("deleted:: " + meterid);			
			return true;
		}
	
					
	}
		
	
	
	//get consumption
	public double getConsumption(long meterid,int month)
	{
		double consume=0;
		MeterReading existingmr=meterReadingRepository.findByMeteridAndMonth(meterid,month);		
		if (existingmr == null) {	
			logger.error("MeterReading  does not exists");
			 throw new EntityExistsException("MeterReading not exist.!");	
		} 
		else
		{
			if(month==1)
			{//if it is january no need check previous(meters are reseted in end of month)
				consume=existingmr.getMeterReading();
				return consume;
			}
			else
			{
				MeterReading previous=meterReadingRepository.findByMeteridAndMonth(meterid,month-1);
				if (previous == null) {						
					logger.error("MeterReading  does not exists");
					 throw new EntityExistsException("MeterReading not exist.!");	
				}
				
				consume=existingmr.getMeterReading()-previous.getMeterReading();
				return consume;				
			}	
			
		}
		
		
	}

	
	
	/**
	 * Save Collection data of a MeterReading 
	 */

	//Save collection of meter reading
	@Override
	public int saveCollection(List<MeterReading> entity) {	
		try
		{			
			for(int i=0;i<entity.size();i++)
			{				
				MeterReading firstmr=entity.get(i);	
				
				//get meterreading list  of a meterID 
				List<MeterReading>meterlist=getMeterReadingListOfMeterId(entity, firstmr);			
				
				//Get sumconsumption for meterreading list  of a meterID 
				int sumConsumption=checkMeterReadingAndGetConsumption(meterlist);	
				
				//Check each meterReading 
				//if not not validate, dont save readinglist of that meterId 		
				if(sumConsumption!=0) 
				{			
					int isReadingValidate=checkConsumeForEachMonth(meterlist,sumConsumption);
					
					if(isReadingValidate!=0) {
						saveAllReadingsOfMeter(meterlist);
				    }	
				}
				
				entity.removeAll(meterlist);
				//clear each meterlist after saving
				meterlist.clear();
			}
			
			logger.debug("collection saved ");
		    return 1;
		    
		}
		catch(Exception ex){
			logger.error("exception occured :ex.message");
			return 0;
		}
		
		
		
	}

	//get MeterReadingList of MeterId
	private List<MeterReading> getMeterReadingListOfMeterId(List<MeterReading> entity,MeterReading firstmr) 
	{
		ArrayList<MeterReading> meterlist=new ArrayList<MeterReading>();	
		
		meterlist.add(firstmr);				
		
		//assume it is 12 rows for a meter
		//get meterreadings of a meter
		for(MeterReading mr:entity)
		{				
			if(mr.getMeterid()==firstmr.getMeterid())
			{
				meterlist.add(mr);		
				entity.remove(mr);					
			}				
		}
		
		return meterlist;
	}
		
	//lets check consumption and reading
	int checkMeterReadingAndGetConsumption(List<MeterReading> entity)
	{			
		int colSize=entity.size();		
		
		//sorted meterreading by Month:1,2,3(JAN,FEB,MAR)
	    Collections.sort(entity);

	    //reading of first month
	    int SumConsumption=entity.get(0).getMeterReading();
	    
		for(int i=1;i<colSize;i++)
		{//check previous month meter reading
			if(entity.get(i).getMeterReading()<entity.get(i-1).getMeterReading())
			{
				logger.error("MeterReading  must be much  than previous");
				return 0;
			}
			else		
			{
				SumConsumption+=entity.get(i).getMeterReading()-entity.get(i-1).getMeterReading();
				logger.debug("SumConsumption:: " + SumConsumption);
			}			
		}
		
		return SumConsumption;		
	}
	
	//lets check consumption and reading
	int checkConsumeForEachMonth(List<MeterReading> entity,int consume)
	{		
		int retVal=0;
		double maxReading,minReading;
		
		for(MeterReading mr:entity)
		{//get fraction of a profile and month
			ProfileAndFraction pf=profileAndFractionRepository.findByMonthAndProfile(mr.getMonth(),mr.getProfile());
			if(pf==null){				
				logger.error("ProfileAndFraction  does not exists");
				return 0;				
			}
			else
			{
				if(pf.getFraction()==0){
					logger.error("getFraction must be different than 0");
					return 0;
				}
				else
				{//check if it is validate
					maxReading=pf.getFraction()*consume;
					minReading=pf.getFraction()*consume;
					
					maxReading+=(maxReading*25)/100;				
					minReading-=(minReading*25)/100;
					
					logger.debug("maxReading:: " + maxReading+" / minReading"+minReading);
					if((mr.getMeterReading()<maxReading) && (mr.getMeterReading()>minReading)){
						logger.error("getMeterReading must be less than max and much than min/ getMeterReading: "+mr.getMeterReading());
						retVal= 0;
					   }
				}
							
			}			
		}
		
		
		return retVal;
		
	}
		
	//save Reading from list
	public void saveAllReadingsOfMeter(List<MeterReading>  entity) 
	{	
		MeterReading newMr;		
		for(MeterReading mr:entity)
		{			
			 newMr=meterReadingRepository.save(mr);
			 logger.debug("Added:: " + newMr);
		}
		
	}
	
	
	/**
	 * Save Collection data of a MeterReading 
	 */



	

	@Override
	public MeterReading getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}
	


}
