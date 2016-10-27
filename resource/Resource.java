package resource;

import client.Message;

public interface Resource {
	void put(Message smt) throws InterruptedException;
	Message take() throws InterruptedException;
}
