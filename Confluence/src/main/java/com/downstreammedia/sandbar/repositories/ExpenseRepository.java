package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

}
