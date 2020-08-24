package br.com.verx.bp.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.verx.bp.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${bp.jwt.expiration}")
	private String expiration;
	
	@Value("${bp.jwt.secret}")
	private String secretKey;

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("VerxBp API")
				.setSubject(user.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public boolean isTokenValid(String tokenCode) {
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(tokenCode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUserId(String tokenCode) {
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(tokenCode).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
