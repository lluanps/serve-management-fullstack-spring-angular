package com.luan.servemanagement.service;

import java.io.IOException;
import java.util.Collection;

import com.luan.servemanagement.model.Server;

public interface ServerService {
	
	Server create(Server server);
	Server ping(String ipAddres) throws IOException;
	Collection<Server> list(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
	
	
}
