package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{
	
	List <Trip> findByCreator_Id(int id);
	List <Trip> findByMembersId(int id);

}
