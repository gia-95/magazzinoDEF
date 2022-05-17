package it.TNetwork.magazzino.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.TNetwork.magazzino.repository.UserRepository;
import it.TNetwork.magazzino.service.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	private UserRepository userRepository;
	
	public SecurityConfiguration (UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
		this.userRepository = userRepository;	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		
		/*
		auth
			.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("pass")).roles("ADMIN")
			.and()
			.withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
			.and()
			.withUser("manager").password(passwordEncoder().encode("pass")).roles("MANAGER");
	*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.cors()
			.and()
			.addFilter(new JwtAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
			.authorizeRequests()
			.antMatchers("*").permitAll()
			.antMatchers("/delivery/API").hasRole("ADMIN");
		
		
		/*
		http
		.authorizeRequests()
		.antMatchers("/index").hasRole("USER")
		.antMatchers("/admin").hasRole("ADMIN") // se hai il ruolo di "ADMIN"
		.antMatchers("/profile/**").authenticated() //può apripre la pagina se sei autenticato (in generale)
													// ** per dire amche quello che viene dopo
		.antMatchers("/manager").hasAnyRole("ADMIN","MANAGER")  // Puoi anche metter epiù ruoli
		.antMatchers("/orders/**").permitAll()
		.antMatchers("/users/**").hasRole("ADMIN") //dovrebbe essere "hasRole("ADMIN")
		.and()
		//.httpBasic(); --> fa comparire il toast di default per inserire username e pasword
		
		.formLogin()
//		.loginProcessingUrl("/singin") --> lo devi specificare nel Form (action e metoto 'post')
											// se i parametri di username e pass non sono quelli convenzionali di spring
		.loginPage("/login").permitAll();
//		.usernameParameter("txtUsernam..")
//		.passwordParameter("txtUsernam..")
//		.and()
//		.logout().logoutRequestMatcher( new vedi 25)
		
		*/
	}

	
	@Bean
	DaoAuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	

}