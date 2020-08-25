package br.com.verx.bp.config.validation;

public class ValidationExceptionDto {

	private String field;
	private String error;

	public ValidationExceptionDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
