package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer>{

}
