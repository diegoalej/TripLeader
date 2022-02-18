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
 * This class is used to map the trip equipment entity
 * 
 * @author Diego Hoyos
 */
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trip_equipment")
public class TripEquipment {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
	
	private int amount;
	
	//Relationship to trip
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	
	//Relationship to equipment
	@OneToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;
	
	/*********METHODS*********/
	@Override
	public int hashCode() {
		return Objects.hash(amount, equipment, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripEquipment other = (TripEquipment) obj;
		return amount == other.amount && Objects.equals(equipment, other.equipment) && id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TripEquipment [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", trip=");
		builder.append(trip);
		builder.append(", equipment=");
		builder.append(equipment);
		builder.append("]");
		return builder.toString();
	}

}
