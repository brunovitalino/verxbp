package br.com.verx.bp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.verx.bp.model.dto.TokenDto;
import br.com.verx.bp.model.dto.UserDto;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody UserDto userDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userDto.toUsernamePasswordAuthenticationToken();
		try {
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String tokenCode = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(tokenCode, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
//			return ResponseEntity.ok("User doesn't exist or credentials are wrong");
		}
	}
	
}
