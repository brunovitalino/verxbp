package br.com.verx.bp.service.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AreaException extends DefaultException {

	private static final long serialVersionUID = 1L;

	private static String entityBroken = "Erro no bairro.";

	public AreaException(String errorMsg, Exception e) {
		super(entityBroken, errorMsg, e);
	}

	public AreaException(DataIntegrityViolationException e) {
		super(entityBroken, e);
	}
	
	public AreaException(ConstraintViolationException e) {
		super(entityBroken, e);
	}

	public AreaException(String errorMsg) {
		super(errorMsg);
	}

	public AreaException(Exception e) {
		super(entityBroken, e);
	}

}
