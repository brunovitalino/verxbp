package br.com.verx.bp.service.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DefaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DefaultException(String errorMsg) {
		super(errorMsg);
	}

	public DefaultException(String entityBroken, String errorMsg, Exception e) {
		super(entityBroken + " " + errorMsg + " " + e.getMessage());
	}

	public DefaultException(String entityBroken, DataIntegrityViolationException e) {
		throw new DataIntegrityViolationException(entityBroken + " " + e.getMostSpecificCause().getMessage());
				
	}
	
	public DefaultException(String entityBroken, ConstraintViolationException e) {
		throw new ConstraintViolationException(entityBroken, e.getConstraintViolations());
		
	}

	public DefaultException(String entityBroken, Exception e) {
		super(entityBroken + " " + e.getMessage());
	}

}
