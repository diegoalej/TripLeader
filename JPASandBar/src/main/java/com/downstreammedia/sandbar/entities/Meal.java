package com.downstreammedia.sandbar.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Meal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="meal_day_id")
	private MealDay dayserved;
	
	@OneToOne
	@JoinColumn(name = "meal_type_id")
	private MealType mealtype;
	
	@OneToMany(mappedBy = "meal")
	private List<Food> ingredients;
	
	//METHODS

	public int getId() {
		return id;
	}

	public List<Food> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Food> ingredients) {
		this.ingredients = ingredients;
	}

	public MealType getMealtype() {
		return mealtype;
	}

	public void setMealtype(MealType mealtype) {
		this.mealtype = mealtype;
	}

	public MealDay getDayserved() {
		return dayserved;
	}

	public void setDayserved(MealDay dayserved) {
		this.dayserved = dayserved;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	
	

}
