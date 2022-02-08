package com.downstreammedia.sandbar.exception;

public class ResourceNotDeletedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotDeletedException(Integer resourceId) {
		super("Could not delete resource with resourceId= " + resourceId);
	}

	public ResourceNotDeletedException(Integer resourceId, String message) {
		super("Could not delete resource with resourceId= " + resourceId + ", Message=" +message);
	}

}
