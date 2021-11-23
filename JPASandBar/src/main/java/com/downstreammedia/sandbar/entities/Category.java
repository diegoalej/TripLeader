package com.downstreammedia.sandbar.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Category {
	
	/*********FIELDS*********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private boolean active; 
	
	//@JsonIgnore
	@ManyToMany(mappedBy = "categories")
	private List<Trip> trips;
	
	@OneToMany(mappedBy = "category")
	private List<Equipment> equipmentList;
	
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", active=");
		builder.append(active);
		builder.append(", trips=");
		builder.append(trips);
		builder.append(", equipmentList=");
		builder.append(equipmentList);
		builder.append("]");
		return builder.toString();
	}

	public Category(int id, String name, String description, boolean active, List<Trip> trips,
			List<Equipment> equipmentList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.trips = trips;
		this.equipmentList = equipmentList;
	}

	public Category() {
		super();
	}
	
}
