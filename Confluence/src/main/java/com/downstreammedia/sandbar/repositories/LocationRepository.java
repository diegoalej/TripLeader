package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
