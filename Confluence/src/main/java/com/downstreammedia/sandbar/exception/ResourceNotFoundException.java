package com.downstreammedia.sandbar.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Integer resourceId) {
		super("Could not find resource with resourceId= " + resourceId);
	}

	public ResourceNotFoundException(Integer resourceId, String message) {
		super("Could not find resource with resourceId= " + resourceId + ", Message=" +message);
	}

}