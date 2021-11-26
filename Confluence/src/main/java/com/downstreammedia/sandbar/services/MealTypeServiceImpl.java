package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.MealType;
import com.downstreammedia.sandbar.repositories.MealTypeRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class MealTypeServiceImpl implements MealTypeService{
	
	@Autowired
	MealTypeRepository mtRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<MealType> findAllMealTypes(){
		return mtRepo.findAll();
	}
	
	@Override
	public MealType findMealTypeById(int id) {
		Optional<MealType> mealtype = mtRepo.findById(id);
		if(mealtype.isPresent()) {
			return mealtype.get();
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public MealType createMealType(MealType mealtype) {
		MealType newMealtype = mtRepo.saveAndFlush(mealtype);
		if(newMealtype != null) {
			return newMealtype;
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public MealType updateMealType(int id, MealType mealtype, String username) {
		Optional<MealType> opt = mtRepo.findById(id);
		if(opt.isPresent() && 
				(userRepo.findByUsername(username).getRole().equals("admin"))
			) {
			MealType managed = opt.get();
			managed.setName(mealtype.getName());
			return mtRepo.saveAndFlush(managed);
		}
		return null;
	}
	
	@Override
	public boolean deleteMealType(int id, String username) {
		boolean result = false;
		Optional<MealType> mealtype = mtRepo.findById(id);
		if(mealtype.isPresent() && 
				(userRepo.findByUsername(username).getRole().equals("admin"))
		) {
			mtRepo.deleteById(id);
			result = true;
		}
		
		return result;
	}

}
