package br.com.verx.bp.service.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerException extends DefaultException {

	private static final long serialVersionUID = 1L;

	private static String entityBroken = "Erro no cliente.";

	public CustomerException(String errorMsg, Exception e) {
		super(entityBroken, errorMsg, e);
	}

	public CustomerException(DataIntegrityViolationException e) {
		super(entityBroken, e);
	}
	
	public CustomerException(ConstraintViolationException e) {
		super(entityBroken, e);
	}

	public CustomerException(String errorMsg) {
		super(errorMsg);
	}

	public CustomerException(Exception e) {
		super(entityBroken, e);
	}

}
