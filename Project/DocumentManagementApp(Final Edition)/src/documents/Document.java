package documents;

/**
 * Abstract base class for all document types.
 * Defines common properties and methods for documents.
 */
public abstract class Document {
    private String name; // The document name

    public Document(String name) {
        this.name = name;
    }

    /**
     * Retrieves the document name.
     * @return The name of the document.
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method to define document-specific behavior.
     */
    public abstract void open();
}
