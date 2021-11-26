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
@Table(name="user_equipment")
public class UserEquipment {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean active;
	
	private String condition; 
	
	private int amount;
	
	@OneToOne
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, equipment, id);
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
		return Objects.equals(creator, other.creator) && Objects.equals(equipment, other.equipment) && id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEquipment [id=");
		builder.append(id);
		builder.append(", active=");
		builder.append(active);
		builder.append(", condition=");
		builder.append(condition);
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

	public UserEquipment(int id, boolean active, String condition, int amount, Equipment equipment, User creator,
			Trip trip) {
		super();
		this.id = id;
		this.active = active;
		this.condition = condition;
		this.amount = amount;
		this.equipment = equipment;
		this.creator = creator;
		this.trip = trip;
	}

	public UserEquipment() {
		super();
	}

	
}
