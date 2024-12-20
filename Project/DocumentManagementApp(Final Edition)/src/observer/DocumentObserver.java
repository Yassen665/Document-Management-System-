package observer;

/**
 * Observer interface for receiving document updates.
 */
public interface DocumentObserver {
    void update(String documentName);
}
