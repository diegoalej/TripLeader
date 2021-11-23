package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Expense {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private int cost;
	
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	
	
	/*********METHODS*********/

	public int getId() {
		return id;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", cost=");
		builder.append(cost);
		builder.append(", date=");
		builder.append(date);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", trip=");
		builder.append(trip);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, creator, date, id, name, trip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return cost == other.cost && Objects.equals(creator, other.creator) && Objects.equals(date, other.date)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(trip, other.trip);
	}

	public Expense(int id, String name, String description, int cost, LocalDateTime date, User creator, Trip trip) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.date = date;
		this.creator = creator;
		this.trip = trip;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	
	
	

}
