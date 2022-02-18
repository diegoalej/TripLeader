package com.downstreammedia.sandbar.exception;

/**
 * Custom exception class for resources not able to update
 * 
 * @author Diego Hoyos
 * 
 */
public class ResourceNotUpdatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotUpdatedException(Integer resourceId) {
		super("Could not update resource with resourceId= " + resourceId);
	}

	public ResourceNotUpdatedException(Integer resourceId, String message) {
		super("Could not update resource with resourceId= " + resourceId + ", Message=" +message);
	}

}