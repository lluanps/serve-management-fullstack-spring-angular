package com.luan.servemanagement;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.luan.servemanagement.model.Server;
import com.luan.servemanagement.repository.ServerRepository;
import com.luan.servemanagement.status.Status;

@SpringBootApplication
public class ServeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeManagementApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160", "Linux", "40 GB",
					"Personal Computer", "http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.55", "Fedora Linux", "16 GB",
					"Dell Tower", "http://localhost:8080/server/image/server2.png",
					Status.SERVER_DOWN));
			serverRepository.save(new Server(null, "192.168.1.48", "MS 2008", "32 GB",
					"Web Server", "http://localhost:8080/server/image/server3.png",
					Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.16", "Red Hat Enterprise Linux", "64 GB",
					"Mail Server", "http://localhost:8080/server/image/server4.png",
					Status.SERVER_DOWN));
		};
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
