package com.luan.servemanagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		return ResponseEntity.ok(
				Response.builder()
					.timeStamp(LocalDate.now())
					.data(Map.of("server", serverServiceImplementation.create(server)))
					.message("Server created")
					.status(HttpStatus.CREATED)
					.statusCode(HttpStatus.CREATED.value())
					.build()
		);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				Response.builder()
					.timeStamp(LocalDate.now())
					.data(Map.of("server", serverServiceImplementation.get(id)))
					.message("Server retrieved")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build()
		);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
				Response.builder()
					.timeStamp(LocalDate.now())
					.data(Map.of("Deleted", serverServiceImplementation.delete(id)))
					.message("Server deleted")
					.status(HttpStatus.OK)
					.statusCode(HttpStatus.OK.value())
					.build()
		);
	}
	
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] serverImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "Downloads/images-sm/" + fileName));	
	}
	
}
