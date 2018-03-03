package com.ConsumeMeter.restful.service;

import java.util.List;

import com.ConsumeMeter.restful.model.ProfileAndFraction;

public interface IProfileAndFraction extends CRUDService<ProfileAndFraction> {
	int saveCollection(List<ProfileAndFraction> entity);
	ProfileAndFraction getByMonthAndProfile(int month,String profile);
	boolean deleteByMonthAndProfile(ProfileAndFraction entity);
}
