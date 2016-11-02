package resource;

public interface Resource<T> {

	T take() throws InterruptedException;

	void put(T message) throws InterruptedException;

}
