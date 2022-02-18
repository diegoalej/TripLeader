package com.downstreammedia.sandbar.exception;

/**
 * Custom exception class for resources not found in db
 * 
 * @author Diego Hoyos
 * 
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Integer resourceId) {
		super("Could not find resource with resourceId= " + resourceId);
	}

	public ResourceNotFoundException(Integer resourceId, String message) {
		super("Could not find resource with resourceId= " + resourceId + ", Message=" +message);
	}

}