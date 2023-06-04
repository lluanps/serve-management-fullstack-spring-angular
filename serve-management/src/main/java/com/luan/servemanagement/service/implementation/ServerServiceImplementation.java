package com.luan.servemanagement.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import javax.transaction.Transactional;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Server get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Server update(Server server) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	private String setServerImgUrl() {
		return null;
	}
	
}
