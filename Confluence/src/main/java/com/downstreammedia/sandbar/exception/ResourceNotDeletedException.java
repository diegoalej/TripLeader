package com.downstreammedia.sandbar.exception;

/**
 * Custom exception class for resources not able to delete
 * 
 * @author Diego Hoyos
 * 
 */
public class ResourceNotDeletedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotDeletedException(Integer resourceId) {
		super("Could not delete resource with resourceId= " + resourceId);
	}

	public ResourceNotDeletedException(Integer resourceId, String message) {
		super("Could not delete resource with resourceId= " + resourceId + ", Message=" +message);
	}

}
