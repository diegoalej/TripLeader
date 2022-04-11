package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.EquipmentService;

/**
 * Class is REST controller for Equipment 
 * 
 * @author Diego Hoyos
 */
@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentServ;
	
	/**
	 * Endpoint returns all Equipment or null
	 * 
	 * @return - a list of all Equipment
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("equipment")
	private ResponseEntity<List<Equipment>> getAllEquipment(){
		List<Equipment> equipment = equipmentServ.findAllEquipment();
		if (equipment.size() > 0) {
			return new ResponseEntity<List<Equipment>>(equipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No equipment found");
		}
	}
	
	/**
	 * Endpoint returns Equipment with specific id value
	 * 
	 * @param id - Equipment id to be found
	 * @return - a Equipment object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("equipment/id/{id}")
	public ResponseEntity<Equipment> getEquipmentWithId(@PathVariable Integer id){
		Equipment equipment = equipmentServ.findEquipmentById(id);
		if (equipment != null) {
			return new ResponseEntity<Equipment>(equipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Equipment does not exist in database");
		}
	}
	
	/**
	 * Endpoint returns Equipment with specific name value
	 * 
	 * @param name - Equipment name to be found
	 * @return - a Equipment object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping(value ="equipment/{name}")
	public ResponseEntity<Equipment> getEquipmentWithName(@PathVariable String name){
		Equipment equipment = equipmentServ.findEquipmentByName(name);
		if (equipment != null) {
			return new ResponseEntity<Equipment>(equipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "Equipment does not exist in database");
		}
	}
	
	/**
	 * Endpoint creates new Equipment
	 * 
	 * @param equipment - Equipment object to be created
	 * @param principal - user making the edit
	 * @return - a Equipment object
	 * @throws - ResourceNotFoundException
	 */
	@PostMapping(value ="equipment", consumes="application/json")
	public ResponseEntity<Equipment> createNewEquipment(
			@RequestBody Equipment equipment, 
			Principal principal
			){
		Equipment newEquipment = equipmentServ.createEquipment(equipment, principal.getName());
		if (newEquipment != null) {
			return new ResponseEntity<Equipment>(newEquipment, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotUpdatedException(0, "Equipment could not be created");
		}
	}
	
	/**
	 * Endpoint edits existing Equipment 
	 * 
	 * @param id - Equipment to be updated
	 * @param equipment - edited Equipment object
	 * @param principal - user performing the edit
	 * @return - a Equipment object or null
	 * @throws - ResourceNotUpdatedException
	 * @throws - ResourceNotFoundException
	 */
	@PutMapping(value ="equipment/{id}", consumes="application/json")
	public ResponseEntity<Equipment> updateExistingEquipment(
			@RequestBody Equipment equipment, 
			@PathVariable int id, 
			Principal principal
			){
		Equipment editEquip = equipmentServ.updateEquipment(id, equipment, principal.getName());
		if (editEquip != null) {
			return new ResponseEntity<Equipment>(editEquip, HttpStatus.OK);
		}
		else {
			throw new ResourceNotUpdatedException(0, "Equipment could not be updated");
		}
	}
	
	/**
	 * Endpoint deletes Equipment with specific id value
	 * 
	 * @param id - Equipment to be deleted
	 * @param principal - user performing the delete
	 * @return - boolean with result
	 * @throws - ResourceNotDeletedException
	 */
	@DeleteMapping("equipment/{id}")
	public  ResponseEntity<Object> deleteEquipment(
			@PathVariable int id, 
			Principal principal
			){
		boolean deleted = false;
			deleted = equipmentServ.deleteEquipment(id, principal.getName());
			if (deleted == true) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
			}
			throw new ResourceNotDeletedException(id, "Equipment could not be deleted");
	}

}
