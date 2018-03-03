package consumeMeter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ConsumeMeter.restful.Application;
import com.ConsumeMeter.restful.model.MeterReading;
import com.ConsumeMeter.restful.service.IMeterReading;


//lezginaksoy



@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@DataJpaTest
public class meterReadingServiceTest {
	
	
	@Autowired
	IMeterReading mRService;

	 @Test
	 public void testgetAll() {
		 List<MeterReading> list = mRService.getAll();
      
		 assertNotNull("failure - expected not null", list);
		 assertEquals("failure - expected list size", 0, list.size());		 
	 }
	
	    @Test
	    public void testdeleteByMeterid() {

	        Long meterid = new Long(1);
	        List<MeterReading> entity = mRService.getByMeterid(meterid);
	        assertNotNull("failure - expected not null", entity);
            
	         mRService.deleteByMeterid(meterid);
	      
		     List<MeterReading> deletedEntity = mRService.getByMeterid(meterid);
		     assertNotNull("failure - expected null", deletedEntity);  
		     assertEquals(0,deletedEntity.size());
	    }
	
	    @Test
	    public void testsave() {
	    	
	    	MeterReading entity = new MeterReading();
	    	Long meterid = new Long(1);
	        entity.setMeterid(meterid);
	        entity.setMonth(1);
	        entity.setProfile("A");
	        entity.setMeterReading(60);
		        
	    	MeterReading createdEntity = mRService.save(entity);
	  
	        assertNotNull("failure - expected not null", createdEntity);
	    }
	    
	    @Test
	    public void testUpdate() {

	    	MeterReading entity = new MeterReading();
	    	Long meterid = new Long(1);
	        entity.setMeterid(meterid);
	        entity.setMonth(1);
	        entity.setProfile("A");
	        entity.setMeterReading(60);
		        
	    	MeterReading createdEntity = mRService.save(entity);
	  
	        assertNotNull("failure - expected not null", createdEntity);
	        
	        
	        createdEntity.setMeterReading(90);	        
	        MeterReading updatedEntity = mRService.update(createdEntity);
	  	 
	        assertNotNull("failure - expected not null", updatedEntity);

	    }
	    
	    
	    @Test
	    public void testsaveCollection() {
	    	
	    	List<MeterReading> mrList = buildMeterReading();	    	
	    	int retVal = mRService.saveCollection(mrList);
	  
	        assertNotNull("failure - expected not null", retVal);
	    }
	  
	    	    
	    @Test
	    public void testgetConsumption()
	    {
	      MeterReading entity = new MeterReading();
	      Long meterid = new Long(1);
	      entity.setMeterid(meterid);
	      entity.setMonth(1);
	      entity.setProfile("A");
	        
	  	  MeterReading createdEntity = mRService.save(entity);	  	  
	  	  assertNotNull("failure - expected not null", createdEntity);
	    	  
	      MeterReading reEntity = mRService.getByMeteridAndMonth(createdEntity);
	      assertNotNull("failure - expected not null", reEntity);
	    	  
	      double consume=mRService.getConsumption(reEntity.getMeterid(),reEntity.getMonth());
	      assertNotNull("failure - expected not null", consume);  	  
	    }
	    
	    
	    
	    private List<MeterReading> buildMeterReading() {
			MeterReading mr1 = new MeterReading(1l,"A",1,20);	
			MeterReading mr2 = new MeterReading(1l,"A",2,50);	
			MeterReading mr3 = new MeterReading(1l,"A",3,80);	
			MeterReading mr4 = new MeterReading(1l,"A",4,100);	
			MeterReading mr5 = new MeterReading(1l,"A",5,120);	
			MeterReading mr6 = new MeterReading(1l,"A",6,140);	
			MeterReading mr7 = new MeterReading(1l,"A",7,170);	
			MeterReading mr8 = new MeterReading(1l,"A",8,200);	
			MeterReading mr9 = new MeterReading(1l,"A",9,230);	
			MeterReading mr10 = new MeterReading(1l,"A",10,260);	
			MeterReading mr11 = new MeterReading(1l,"A",11,300);	
			MeterReading mr12 = new MeterReading(1l,"A",12,360);	
			
			List<MeterReading> mrList = Arrays.asList(mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,mr11,mr12);
			return mrList;
		}
	    
}
