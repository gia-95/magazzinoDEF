package it.TNetwork.magazzino.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SignOptions {
	
	@Value("${tnstock.security.signOptions.issuer}")
	private String issuer;
	
	@Value("${tnstock.security.signOptions.subject}")
	private String subject;
	
	@Value("${tnstock.security.signOptions.ttlMillis}")
	private long ttlMillis;

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getTtlMillis() {
		return ttlMillis;
	}

	public void setTtlMillis(long ttlMillis) {
		this.ttlMillis = ttlMillis;
	}
}