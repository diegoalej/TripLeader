package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.MealDay;
import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.MealDayRepository;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;


@Service
public class MealDayServiceImpl implements MealDayService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MealDayRepository mdRepo;
	
	@Autowired
	TripRepository tripRepo;
	
	@Override
	public List<MealDay> findAllMealDays() {
		return mdRepo.findAll();
	}
	
	@Override
	public MealDay findMealDayById(int id) {
		Optional<MealDay> mealDay = mdRepo.findById(id);
		if(mealDay.isPresent()) {
			return mealDay.get();
		}
		else {
			return null;
		}
	}
	
	public List<MealDay> findMealDayByTripId(int id) {
		List <MealDay> mealDay = mdRepo.findByTrip_Id(id);
		if(mealDay != null) {
			return mealDay;
		}
		else {
			return null;
		}
	}
	
	@Override
	public MealDay updateMealDay(int id, MealDay mealDay, String username) {
		Optional<MealDay> oldMealDay = mdRepo.findById(id);
		MealDay managedMealDay = null;
		if (oldMealDay.isPresent()) {
			managedMealDay = oldMealDay.get();
			managedMealDay.setId(id);
			managedMealDay.setSleep(mealDay.getSleep());
			managedMealDay.setDescription(mealDay.getDescription());
			managedMealDay.setDate(mealDay.getDate());
			if (userRepo.findByUsername(username) != null) {
				return mdRepo.saveAndFlush(managedMealDay);
			}			
		}
		return null;
	}
	
	@Override
	public MealDay createMealDay(MealDay mealDay, String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Trip> trip = tripRepo.findById(id);
		MealDay newMealDay = null;
		if (user != null) {
			mealDay.setTrip(trip.get());
			newMealDay = mdRepo.saveAndFlush(mealDay);
		}
		return newMealDay;			
	}
	
	@Override
	public boolean deleteMealDay(int id, String username) {
		boolean answer = false;
		Optional<MealDay> mealDay = mdRepo.findById(id);
		if (mealDay.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			mdRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}

}
