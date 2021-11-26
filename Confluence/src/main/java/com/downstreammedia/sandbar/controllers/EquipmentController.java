package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.Equipment;
import com.downstreammedia.sandbar.entities.Location;
import com.downstreammedia.sandbar.services.EquipmentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentServ;
	
	@GetMapping("equipment")
	private List<Equipment> getAllEquipment(HttpServletResponse response){
		List<Equipment> equipment = equipmentServ.findAllEquipment();
		if (equipment.size() > 0) {
			return equipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("equipment/id/{id}")
	public Equipment getEquipmentWithId(@PathVariable Integer id, HttpServletResponse response){
		Equipment equipment = equipmentServ.findEquipmentById(id);
		if (equipment != null) {
			return equipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("equipment/{name}")
	public Equipment getEquipmentWithId(@PathVariable String name, HttpServletResponse response){
		Equipment equipment = equipmentServ.findEquipmentByName(name);
		if (equipment != null) {
			return equipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("equipment")
	public Equipment createNewEquipment(
			@RequestBody Equipment equipment, 
			HttpServletResponse response,
			Principal principal
			){
		Equipment newEquipment = equipmentServ.createEquipment(equipment, principal.getName());
		if (newEquipment != null) {
			return newEquipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("equipment/{id}")
	public Equipment updateExistingEquipment(
			@RequestBody Equipment equipment, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Equipment editLocation = equipmentServ.updateEquipment(id, equipment, principal.getName());
		if (editLocation != null) {
			return editLocation;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("equipment/{id}")
	public void deleteLocation(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
//		Location loc = locationServ.findLocationById(id);
//		if(loc.getReviews().size() > 0) {
//			for (Review review : loc.getReviews()) {
//				revSvc.deleteReview(review.getId(), principal.getName());
//			}
//		}
		try {
			deleted = equipmentServ.deleteEquipment(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
