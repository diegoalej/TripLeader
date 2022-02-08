package com.downstreammedia.sandbar.exception;

import static java.util.Collections.singletonMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.downstreammedia.sandbar.model.utils.ErrorMessage;
import com.downstreammedia.sandbar.model.utils.ResponseWrapper;
import com.downstreammedia.sandbar.model.utils.RestErrorList;

/**
 *
 * @author diegoalej
 * @version
 * @since Feb 2, 2022
 */


@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler {


    /**
     * handleException - Handles all the Exception recieving a request, responseWrapper.
     *@param request
     *@param responseWrapper
     *@return ResponseEntity<ResponseWrapper>
     * @user diegoalej
     * @since Feb 2, 2022 
     */
	@ExceptionHandler(value = { Exception.class })
    public @ResponseBody ResponseEntity<ResponseWrapper> handleAPIException(HttpServletRequest request,
            ResponseWrapper responseWrapper){
		
		RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage("message","message2"));
        responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);
        return ResponseEntity.ok(responseWrapper);
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
		return ResponseEntity.ok(responseWrapper);
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
		
		RestErrorList errorList = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage(ex.getMessage(), ex.getMessage()));
		ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorList);
		return ResponseEntity.ok(responseWrapper);
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
		
		RestErrorList errorList = new RestErrorList(HttpStatus.NOT_FOUND, new ErrorMessage(ex.getMessage(), ex.getMessage()));
		ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_FOUND), errorList);
		return ResponseEntity.ok(responseWrapper);
	}
	
}