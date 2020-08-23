package br.com.verx.bp.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AreaException extends Exception {

	private static final long serialVersionUID = 1L;

	public AreaException(String msg) {
		super("Area error. " + msg);
	}

}
