package br.com.verx.bp.model.dto;

public class TokenDto {

	private String tokenCode;
	private String type;

	public TokenDto(String tokenCode, String type) {
		this.tokenCode = tokenCode;
		this.type = type;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public String getType() {
		return type;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public void setType(String type) {
		this.type = type;
	}

}
