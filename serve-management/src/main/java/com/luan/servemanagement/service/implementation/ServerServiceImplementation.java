package com.luan.servemanagement.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.luan.servemanagement.model.Server;
import com.luan.servemanagement.repository.ServerRepository;
import com.luan.servemanagement.service.ServerService;
import com.luan.servemanagement.status.Status;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
	private final ServerRepository serverRepository;
	
	 
	@Override
	public Server create(Server server) {
		log.info("Saving new Server: {}", server.getName());
		server.setImgUrl(setServerImgUrl());
		return serverRepository.save(server);
	}

	@Override
	public Server ping(String ipAddres) throws IOException {
		log.info("Ping server IP: {}", ipAddres);
		Server server = serverRepository.findByIpAddress(ipAddres);
		InetAddress address = InetAddress.getByName(ipAddres);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepository.save(server);
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id: {}", id);
		return serverRepository.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating Server: {}", server.getName());
		return serverRepository.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by: {}", id);
		serverRepository.deleteById(id);
		return Boolean.TRUE;
	}


	private String setServerImgUrl() {
		return null;
	}
	
}
