package com.downstreammedia.sandbar.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Meal {
	
	/*********FIELDS*********/
	
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
	
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public MealDay getDayserved() {
		return dayserved;
	}

	public void setDayserved(MealDay dayserved) {
		this.dayserved = dayserved;
	}

	public MealType getMealtype() {
		return mealtype;
	}

	public void setMealtype(MealType mealtype) {
		this.mealtype = mealtype;
	}

	public List<Food> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Food> ingredients) {
		this.ingredients = ingredients;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		return Objects.equals(creator, other.creator) && id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Meal [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", dayserved=");
		builder.append(dayserved);
		builder.append(", mealtype=");
		builder.append(mealtype);
		builder.append(", ingredients=");
		builder.append(ingredients);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}

	public Meal(int id, String name, String description, User creator, MealDay dayserved, MealType mealtype,
			List<Food> ingredients, Location location) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.creator = creator;
		this.dayserved = dayserved;
		this.mealtype = mealtype;
		this.ingredients = ingredients;
		this.location = location;
	}

	public Meal() {
		super();
	}
	
}
