package resource;

public interface IResource {
	void put(Message smt) throws InterruptedException;
	Message take() throws InterruptedException;
}
