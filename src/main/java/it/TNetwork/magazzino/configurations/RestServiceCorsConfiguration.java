

package it.TNetwork.magazzino.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestServiceCorsConfiguration {                                    


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
					.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
	                .allowedHeaders("Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Allow-Origin",
	                		"Referer", "User-Agent", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Token");
			}
		};
	}



}