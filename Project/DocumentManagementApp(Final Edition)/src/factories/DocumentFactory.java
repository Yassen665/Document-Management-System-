package factories;

import documents.Document;
import documents.PDFDocument;
import documents.WordDocument;
import documents.ExcelDocument;

/**
 * Factory class for creating document objects based on their file type.
 * Implements the Factory Design Pattern to decouple document creation logic.
 */
public class DocumentFactory {
    /**
     * Creates a document instance based on the file type.
     * @param type The file type (e.g., "pdf", "docx", "xlsx").
     * @param name The file name.
     * @return An instance of the appropriate Document subclass.
     */
    public static Document createDocument(String type, String name) {
        switch (type.toLowerCase()) {
            case "pdf":
                return new PDFDocument(name);
            case "docx": // Support for modern Word documents
                return new WordDocument(name);
            case "xlsx": // Support for modern Excel documents
                return new ExcelDocument(name);
            default:
                throw new IllegalArgumentException("Unsupported document type: " + type);
        }
    }
}
