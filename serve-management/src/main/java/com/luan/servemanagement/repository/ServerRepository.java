package com.luan.servemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.servemanagement.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
	
	Server findByIpAddress(String ipAddress);
	
}
