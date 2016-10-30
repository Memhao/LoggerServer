package server;

import java.util.List;

import client.ClientThread;

public interface IServer {
	void subscribeClient(ClientThread client);
	void subscribeClients(List<ClientThread> clients);
	
	void start();
	void stop();
}
