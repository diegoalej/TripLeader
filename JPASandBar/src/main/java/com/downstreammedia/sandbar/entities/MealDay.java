package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="meal_day")
public class MealDay {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime date;
	
	private String sleep;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;    

	@OneToMany(mappedBy = "dayserved")
	private Set<Meal> meals;


	/*********METHODS*********/
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getSleep() {
		return sleep;
	}


	public void setSleep(String sleep) {
		this.sleep = sleep;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Trip getTrip() {
		return trip;
	}


	public void setTrip(Trip trip) {
		this.trip = trip;
	}


	public Set<Meal> getMeals() {
		return meals;
	}


	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}


	@Override
	public int hashCode() {
		return Objects.hash(date, id, trip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealDay other = (MealDay) obj;
		return Objects.equals(date, other.date) && id == other.id && Objects.equals(trip, other.trip);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MealDay [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", sleep=");
		builder.append(sleep);
		builder.append(", description=");
		builder.append(description);
		builder.append(", trip=");
		builder.append(trip);
		builder.append(", meals=");
		builder.append(meals);
		builder.append("]");
		return builder.toString();
	}


	public MealDay(int id, LocalDateTime date, String sleep, String description, Trip trip,
			Set<Meal> meals) {
		super();
		this.id = id;
		this.date = date;
		this.sleep = sleep;
		this.description = description;
		this.trip = trip;
		this.meals = meals;
	}


	public MealDay() {
		super();
	}
	
}
