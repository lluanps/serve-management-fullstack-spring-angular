package com.luan.servemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.servemanagement.model.Response;
import com.luan.servemanagement.model.Server;
import com.luan.servemanagement.service.implementation.ServerServiceImplementation;
import com.luan.servemanagement.status.Status;

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
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		Server server = serverServiceImplementation.ping(ipAddress);
		return ResponseEntity.ok(
				Response.builder()
					.timeStamp(LocalDate.now())
					.data(Map.of("server", server))
					.message(server.getStatus() == Status.SERVER_UP ? "Ping success!" : "Ping failed!")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build()
		);

	}
}
