package com.ConsumeMeter.restful.model;

import java.util.Comparator;

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
@Table(name = "ProfileAndFraction")
public class ProfileAndFraction  implements Comparable<ProfileAndFraction>  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
    //month ;month=2-->FEB,3-->MARCH.....
	@NotNull(message = "month  is required" )
	@Column(name = "month")
    private int month;
	
	@NotNull(message = "profile  is required" )
	@Column(name = "profile", length = 1)
	private String profile ;
	
	@NotNull(message = "fraction  is required" )
	@Column(name = "fraction")
	private double  fraction;

	public int getMonth() {
		return month;
	}

	
   public ProfileAndFraction() {
		
	}
		
	public ProfileAndFraction(int month, String profile, double fraction) {
		super();
		this.month = month;
		this.profile = profile;
		this.fraction = fraction;
	}

	
	   public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    
	public void setMonth(int month) {
		this.month = month;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public double getFraction() {
		return fraction;
	}

	public void setFraction(double fraction) {
		this.fraction = fraction;
	}
	
	
	 public static Comparator<ProfileAndFraction> ProfileComparator = new Comparator<ProfileAndFraction>() {
		   
		   @Override
	        public int compare(ProfileAndFraction e1, ProfileAndFraction e2) {
	            return e1.getProfile().compareTo(e2.getProfile());
	        }
	    };

	@Override
	public int compareTo(ProfileAndFraction o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
