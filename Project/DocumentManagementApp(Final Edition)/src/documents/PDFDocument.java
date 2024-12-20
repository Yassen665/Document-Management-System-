package documents;

/**
 * Concrete class representing a PDF document.
 */
public class PDFDocument extends Document {
    public PDFDocument(String name) {
        super(name);
    }

    @Override
    public void open() {
        System.out.println("Opening PDF Document: " + getName());
    }
}
