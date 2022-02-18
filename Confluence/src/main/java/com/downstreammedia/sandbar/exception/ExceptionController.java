package com.downstreammedia.sandbar.exception;

import static java.util.Collections.singletonMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.downstreammedia.sandbar.model.utils.ErrorMessage;
import com.downstreammedia.sandbar.model.utils.ResponseWrapper;
import com.downstreammedia.sandbar.model.utils.RestErrorList;

/**
 *Global exception configuration class
 *
 * @author diegoalej
 * @version
 * @since Feb 2, 2022
 */
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    /**
     * handleExceptionInternal - Override for customizing error body for all exceptions.
	 * @param ex the exception
	 * @param body the body for the response
	 * @param headers the headers for the response
	 * @param status the response status
	 * @param request the current request
     * @return ResponseEntity<ResponseWrapper>
     * @user diegoalej
     * @since Feb 2, 2022 
     */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage(ex.getMessage(), ex.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);
		return new ResponseEntity<>(responseWrapper, headers, status);
	}
    
	/**
	 * handleIOException - Handles the Authentication Exceptions of the application. 
	 *@param request
	 *@param exception
	 *@return ResponseEntity<ResponseWrapper>
	 * @user diegoalej
	 * @since Feb 2, 2022 
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseWrapper> handleIOException(RuntimeException e, HttpServletRequest request){
    	
    	RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage(e.getMessage(), e.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);
        
      
        return ResponseEntity.ok(responseWrapper);
	}
	
	/**
	 * resourceNotFoundHandler - Handles instances of resources not in db. 
	 *@param request
	 *@param exception
	 *@return ResponseEntity<ResponseWrapper>
	 * @user diegoalej
	 * @since Feb 6, 2022 
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseWrapper> resourceNotFoundHandler(ResourceNotFoundException ex, HttpServletRequest request) {
		
		RestErrorList errorList = new RestErrorList(HttpStatus.NOT_FOUND, new ErrorMessage(ex.getMessage(), ex.getMessage()));
		ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_FOUND), errorList);
		return new ResponseEntity<ResponseWrapper>(responseWrapper, HttpStatus.NOT_FOUND);
	}

	/**
	 * resourceNotUpdatedHandler - Handles instances of resources unable to update. 
	 *@param request
	 *@param exception
	 *@return ResponseEntity<ResponseWrapper>
	 * @user diegoalej
	 * @since Feb 6, 2022 
	 */
	@ExceptionHandler(ResourceNotUpdatedException.class)
	public ResponseEntity<ResponseWrapper> resourceNotUpdatedHandler(ResourceNotFoundException ex, HttpServletRequest request) {
		
		RestErrorList errorList = new RestErrorList(HttpStatus.CONFLICT, new ErrorMessage(ex.getMessage(), ex.getMessage()));
		ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.CONFLICT), errorList);
		return new ResponseEntity<ResponseWrapper>(responseWrapper, HttpStatus.CONFLICT);
	}

	/**
	 * resourceNotDeletedHandler - Handles instances of resources unable to delete. 
	 *@param request
	 *@param exception
	 *@return ResponseEntity<ResponseWrapper>
	 * @user diegoalej
	 * @since Feb 6, 2022 
	 */
	@ExceptionHandler(ResourceNotDeletedException.class)
	public ResponseEntity<ResponseWrapper> resourceNotDeletedHandler(ResourceNotDeletedException ex, HttpServletRequest request) {
		
		RestErrorList errorList = new RestErrorList(HttpStatus.BAD_REQUEST, new ErrorMessage(ex.getMessage(), ex.getMessage()));
		ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.BAD_REQUEST), errorList);
		return new ResponseEntity<ResponseWrapper>(responseWrapper, HttpStatus.BAD_REQUEST);
	}
	
}