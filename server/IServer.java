package server;

import java.util.concurrent.ConcurrentLinkedQueue;

import client.Client;

public interface IServer {

	
	void start();
	void stop();
	void subscribeClient(Client client);
	void subscribeClients(ConcurrentLinkedQueue<Client> clients);
}
