package it.TNetwork.magazzino.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class TokenUtil { 
	
	@Autowired
    ResourceLoader resourceLoader;
	
	@Autowired
	private SignOptions signOptions;
	
	private String PRIVATE_KEY;

	/**
	 * create token jwt
	 * @param username
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	public String createJWT(String username) throws IOException, URISyntaxException {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.readPrivateKey());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(username)
				.setIssuedAt(now)
				.setSubject(this.signOptions.getSubject())
				.setIssuer(this.signOptions.getIssuer())
				.signWith(signatureAlgorithm, signingKey);

		//if it has been specified, let's add the expiration
		if (this.signOptions.getTtlMillis() > 0) {
			long expMillis = nowMillis + this.signOptions.getTtlMillis();
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}  

		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	/**
	 * verify token jwt
	 * @param jwt
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 * @throws SignatureException 
	 * @throws MalformedJwtException 
	 * @throws UnsupportedJwtException 
	 * @throws ExpiredJwtException 
	 * @throws ApplicationException 
	 */
	public String decodeJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, IOException, URISyntaxException {
		//This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = null;
		if(jwt != null && !jwt.equals("")) {			
			claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(this.readPrivateKey()))
					.parseClaimsJws(jwt).getBody();
			
		}
		return claims.getId();
	}

	/**
	 * read private key
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private String readPrivateKey() throws IOException, URISyntaxException {
		if(this.PRIVATE_KEY == null) {			
			Resource resource = resourceLoader.getResource("classpath:static/secret.pem");
	        InputStream inputStream = resource.getInputStream();
	        try {
	            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
	            this.PRIVATE_KEY = new String(bdata, StandardCharsets.UTF_8);
	        } catch (IOException e) {
	        }
		}

		return this.PRIVATE_KEY;
	}
	

}