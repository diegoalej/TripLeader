package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
