package com.downstreammedia.sandbar.entities;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used to map the user equipment entity
 * 
 * @author Diego Hoyos
 */
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_equipment")
public class UserEquipment {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
		
	private int amount;
	
	@OneToOne
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;
	
	//Relationship to user creator
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
	public int hashCode() {
		return Objects.hash(creator, id, trip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEquipment other = (UserEquipment) obj;
		return Objects.equals(creator, other.creator) && id == other.id && Objects.equals(trip, other.trip);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEquipment [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", equipment=");
		builder.append(equipment);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", trip=");
		builder.append(trip);
		builder.append("]");
		return builder.toString();
	}
	
}
