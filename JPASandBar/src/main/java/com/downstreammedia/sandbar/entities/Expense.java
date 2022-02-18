package com.downstreammedia.sandbar.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used to map the trip expense entity
 * 
 * @author Diego Hoyos
 */
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
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
	
	//Relationship to user 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User creator;
	
	//Relationship to trip
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	
	
	/*********METHODS*********/
	
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

}
