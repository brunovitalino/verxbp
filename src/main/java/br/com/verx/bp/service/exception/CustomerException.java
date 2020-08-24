package br.com.verx.bp.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerException(String msg) {
		super("Customer error. " + msg);
	}

}
