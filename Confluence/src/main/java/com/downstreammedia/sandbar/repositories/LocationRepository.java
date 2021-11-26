package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	
	List<Location> findByName(String name);

}
