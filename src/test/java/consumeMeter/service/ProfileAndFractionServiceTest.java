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
import com.ConsumeMeter.restful.model.ProfileAndFraction;
import com.ConsumeMeter.restful.service.IProfileAndFraction;


//lezginaksoy


@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
@DataJpaTest
public class ProfileAndFractionServiceTest {
	
	
	@Autowired
	IProfileAndFraction pfService;

	 @Test
	 public void testgetAll() {
		 List<ProfileAndFraction> list = pfService.getAll();
      
		 assertNotNull("failure - expected not null", list);
		 assertEquals("failure - expected list size", 0, list.size());		 
	 }

	    @Test
	    public void testfractionSumisDifferentThanOne() {
	    	
	    	ProfileAndFraction entity = new ProfileAndFraction();
	    	entity.setFraction(0.2);
	        entity.setMonth(1);
	        entity.setProfile("A");
	           
	        ProfileAndFraction createdEntity = pfService.save(entity);
	        assertEquals(null,createdEntity);
	    }
	    
	    
	    @Test
	    public void testsaveCollection() {
	    	
	    	List<ProfileAndFraction> mrList = buildProfileAndFraction();	    	
	    	int retVal = pfService.saveCollection(mrList);
	  
	        assertNotNull("failure - expected not null", retVal);
	    }
	  
	    
	    @Test
	    public void testDeleteProfile() {
	    	
	    	List<ProfileAndFraction> mrList = buildProfileAndFraction();	    	
	    	int retVal = pfService.saveCollection(mrList);
	  
	        assertNotNull("failure - expected not null", retVal);
	        
	        ProfileAndFraction pf=new ProfileAndFraction();
	        pf.setFraction(0.1);
	        pf.setMonth(1);
	        pf.setProfile("A");
	        	        
	        boolean result=pfService.deleteByMonthAndProfile(pf);
	        assertEquals(true,result);	        
	        
	    }
	    
	    
	    @Test
	    public void testgetByMonthAndProfile() {
	    	
	    	List<ProfileAndFraction> mrList = buildProfileAndFraction();	    	
	    	int retVal = pfService.saveCollection(mrList);
	  
	        assertNotNull("failure - expected not null", retVal);
	        
	        ProfileAndFraction pf=new ProfileAndFraction();
	        pf.setFraction(0.1);
	        pf.setMonth(1);
	        pf.setProfile("A");
	        	        
	        ProfileAndFraction result=pfService.getByMonthAndProfile(pf.getMonth(), pf.getProfile());
	        assertNotNull("failure - expected not null", result);
	    }
	    
	    
	    
	    
	    
	    
	    private List<ProfileAndFraction> buildProfileAndFraction() {
			ProfileAndFraction mr1 = new ProfileAndFraction(1,"A",0.1);	
			ProfileAndFraction mr2 = new ProfileAndFraction(2,"A",0.1);	
			ProfileAndFraction mr3 = new ProfileAndFraction(3,"A",0.1);	
			ProfileAndFraction mr4 = new ProfileAndFraction(4,"A",0.1);		
			ProfileAndFraction mr5 = new ProfileAndFraction(5,"A",0.1);	
			ProfileAndFraction mr6 = new ProfileAndFraction(6,"A",0.1);		
			ProfileAndFraction mr7 = new ProfileAndFraction(7,"A",0.1);	
			ProfileAndFraction mr8 = new ProfileAndFraction(8,"A",0.1);	
			ProfileAndFraction mr9 = new ProfileAndFraction(9,"A",0.05);	
			ProfileAndFraction mr10 = new ProfileAndFraction(10,"A",0.05);		
			ProfileAndFraction mr11 = new ProfileAndFraction(11,"A",0.05);		
			ProfileAndFraction mr12 = new ProfileAndFraction(12,"A",0.05);		
			
			List<ProfileAndFraction> mrList = Arrays.asList(mr1,mr2,mr3,mr4,mr5,mr6,mr7,mr8,mr9,mr10,mr11,mr12);
			return mrList;
		}
	    
}
