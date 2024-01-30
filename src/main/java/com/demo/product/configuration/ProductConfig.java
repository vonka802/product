package com.demo.product.configuration;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.product.model.ProductDTO;

@Configuration
@EnableAspectJAutoProxy
public class ProductConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails vivek1 = User.builder().username("vivek1").password("{noop}chandra").roles("EMPLOYEE").build();

		UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE", "MANAGER")
				.build();

		UserDetails mary = User.builder().username("mary").password("{noop}test123")
				.roles("EMPLOYEE", "MANAGER", "ADMIN").build();

		return new InMemoryUserDetailsManager(vivek1, john, mary);
	}

	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.GET, "/inventory/products")
				.hasRole("EMPLOYEE").requestMatchers(HttpMethod.GET, "/inventory/products/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.POST, "/inventory/products").hasRole("MANAGER")
				.requestMatchers(HttpMethod.POST, "/inventory/products").hasRole("MANAGER")
				.requestMatchers(HttpMethod.PUT, "/inventory/products").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/inventory/products/**").hasRole("ADMIN"));

		http.httpBasic(Customizer.withDefaults());

		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	@Bean(name = "ProductDTO")
	public ProductDTO getProductDTO() {
		return new ProductDTO();
	}

}
