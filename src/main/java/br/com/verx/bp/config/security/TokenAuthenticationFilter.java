package br.com.verx.bp.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.verx.bp.model.User;
import br.com.verx.bp.repository.UserRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository userRepository;

	public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenCode;
		
		String tokenCodeWithType = request.getHeader("Authorization");
		if (tokenCodeWithType == null || tokenCodeWithType.isEmpty() || !tokenCodeWithType.startsWith("Bearer ")) {
			tokenCode = null;
		} else {
			tokenCode = tokenCodeWithType.substring(7, tokenCodeWithType.length());
		}
		
		if (tokenService.isTokenValid(tokenCode)) {
			Long userId = tokenService.getUserId(tokenCode);
			Optional<User> userOpt = userRepository.findById(userId);
			if (userOpt.isPresent()) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userOpt.get(), null, userOpt.get().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		
		filterChain.doFilter(request, response);
	}

}
