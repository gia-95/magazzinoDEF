package it.TNetwork.magazzino.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.TNetwork.magazzino.service.utils.TokenUtil;
import it.TNetwork.magazzino.service.utils.Utility;

@Component
@Order(1)
public class TokenFilter implements Filter {

	Logger logger = Logger.getLogger(TokenFilter.class.getName());

	@Autowired
	private TokenUtil tokenUtil;

	private String[] GUEST_PATHS = { "/v2/api-docs", "/swagger-ui", "/swagger-resources", "/webjars", "/csrf",
			"/auth" };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		CustomHttpServletRequest req = new CustomHttpServletRequest((HttpServletRequest) request);

		HttpServletResponse res = (HttpServletResponse) response;

		// CORS
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST, DELETE");
		res.addHeader("Access-Control-Allow-Headers", "*");

		// build req ID (current timestamp) e imposta header
		long requestID = new Date().getTime();
		req.putHeader("requestId", Long.toString(requestID));

		String requestMsg = String.format("Incoming Request '%s %s' from '%s' Request ID: %s", req.getMethod(),
				req.getRequestURI(), req.getRemoteAddr(), requestID);
		logger.log(Level.INFO, requestMsg);

		if (this.isGuestPath(req)) {
			chain.doFilter(req, response);
		} else {
			// validate token
			String token = req.getHeader("Token");
			try {
				req.putHeader("userId", this.tokenUtil.decodeJWT(token));
				chain.doFilter(req, response);
			} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
					| IllegalArgumentException | IOException | URISyntaxException  e) {
				// TODO Auto-generated catch block
				System.out.println("Token non presente o scaduto.");
				e.printStackTrace();
			}

		}
	}

	/**
	 * verifica se la chiamata è autenticata o meno in base all'URI della request
	 * (path relativo)
	 * 
	 * @param req
	 * @return
	 */
	private boolean isGuestPath(CustomHttpServletRequest req) {
		boolean isGuest = false;
		if (req.getMethod().equalsIgnoreCase("OPTIONS") || this.isFromSwagger(req.getHeader("referer"))) {
			isGuest = true;
		} else {
			for (String relativeGuestPath : GUEST_PATHS) {
				String guestPath = relativeGuestPath;
				isGuest = req.getRequestURI().startsWith(guestPath);
				if (isGuest) {
					break;
				}
			}
		}
		return isGuest;
	}

	/**
	 * verifica se è una chiamata da swagger
	 * 
	 * @param referer
	 * @return
	 */
	private boolean isFromSwagger(String referer) {
		boolean isFromSwagger = Utility.checkValid(referer) && referer.contains("swagger-ui");
		return isFromSwagger;
	}

}