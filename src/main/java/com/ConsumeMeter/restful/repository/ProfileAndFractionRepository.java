package com.ConsumeMeter.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ConsumeMeter.restful.model.ProfileAndFraction;



public interface ProfileAndFractionRepository extends JpaRepository<ProfileAndFraction,Long> {

	ProfileAndFraction findByMonthAndProfile(int month,String profile);
	List<ProfileAndFraction> findByProfile(String profile);
	void deleteByMonthAndProfile(int month,String profile);	
	
}
