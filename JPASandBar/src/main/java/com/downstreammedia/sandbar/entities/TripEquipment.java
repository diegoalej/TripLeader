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

@Entity
@Table(name="trip_equipment")
public class TripEquipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	
	@OneToOne
	@JoinColumn(name="equipment_id")
	private Equipment equipment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TripEquipment [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", trip=");
		builder.append(trip);
		builder.append(", equipment=");
		builder.append(equipment);
		builder.append("]");
		return builder.toString();
	}

	public TripEquipment(int id, boolean active, Trip trip, Equipment equipment) {
		super();
		this.id = id;
		this.active = active;
		this.trip = trip;
		this.equipment = equipment;
	}

	public TripEquipment() {
		super();
	}	

}
