package com.example.rentals_backend.service;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JWTTokenService {

	private final JwtEncoder jwtEncoder;
	private final Duration expiration;
	private final String issuer;

	public JWTTokenService(
			JwtEncoder jwtEncoder,
			@Value("${app.jwt.exp-min}") int expirationMinutes,
			@Value("${app.jwt.issuer}") String issuer) {
		this.jwtEncoder = jwtEncoder;
		this.expiration = Duration.ofMinutes(expirationMinutes);
		this.issuer = issuer;
	}

	public String generateToken(Authentication auth) {
		Instant now = Instant.now();
		String scope = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer(issuer)
				.issuedAt(now)
				.expiresAt(now.plus(expiration))
				.subject(auth.getName())
				.claim("scope", scope)
				.build();

		JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).type("JWT").build();

		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}

}
