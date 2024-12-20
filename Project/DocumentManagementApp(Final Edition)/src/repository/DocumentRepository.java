package repository;

import documents.Document;
import observer.DocumentObserver;
import main.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that manages document storage and observers.
 */
public class DocumentRepository {
    private static DocumentRepository instance; // Singleton instance
    private List<Document> documents = new ArrayList<>();
    private List<DocumentObserver> observers = new ArrayList<>();

    // Private constructor for Singleton
    private DocumentRepository() {}

    /**
     * Returns the single instance of DocumentRepository.
     * @return Singleton instance.
     */
    public static synchronized DocumentRepository getInstance() {
        if (instance == null) {
            instance = new DocumentRepository();
        }
        return instance;
    }

    /**
     * Adds a document to the repository and notifies observers.
     * @param document The document to add.
     */
    public void addDocument(Document document) {
        documents.add(document); // Add to memory
        notifyObservers(document.getName()); // Notify observers
        saveToDatabase(document); // Save to database
    }

    /**
     * Saves the document to the SQL database.
     * @param document The document to save.
     */
    private void saveToDatabase(Document document) {
        String sql = "INSERT INTO Documents (name, type, path) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, document.getName());
            stmt.setString(2, document.getClass().getSimpleName());
            stmt.setString(3, "UploadedFiles/" + document.getName()); // Placeholder path

            stmt.executeUpdate();
            System.out.println("Document saved to database: " + document.getName());
        } catch (SQLException e) {
            System.err.println("Error saving document to database: " + e.getMessage());
        }
    }

    /**
     * Adds an observer to the repository.
     * @param observer The observer to add.
     */
    public void addObserver(DocumentObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of a new document addition.
     * @param documentName The name of the document added.
     */
    private void notifyObservers(String documentName) {
        for (DocumentObserver observer : observers) {
            observer.update(documentName);
        }
    }
}
