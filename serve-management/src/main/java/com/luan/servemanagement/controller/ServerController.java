package com.luan.servemanagement.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.servemanagement.model.Response;
import com.luan.servemanagement.service.ServerService;
import com.luan.servemanagement.service.implementation.ServerServiceImplementation;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/servers")
@RequiredArgsConstructor
public class ServerController {
	
	private final ServerServiceImplementation serverServiceImplementation;
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity.ok(
				Response.builder()
					.timeStamp(LocalDate.now())
					.data(Map.of("servers", serverServiceImplementation.list(30)))
					.message("Servers retrieved")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build()
				);
				
	}

}
