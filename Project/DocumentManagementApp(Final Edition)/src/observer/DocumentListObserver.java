package observer;

/**
 * Concrete observer that logs document updates to the console.
 */
public class DocumentListObserver implements DocumentObserver {
    @Override
    public void update(String documentName) {
        System.out.println("Document added: " + documentName);
    }
}
