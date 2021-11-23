package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
