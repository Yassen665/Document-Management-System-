package commands;

import repository.DocumentRepository;
import documents.Document;
import factories.DocumentFactory;

import javax.swing.*;

/**
 * Command for uploading documents.
 * Implements the Command Design Pattern to encapsulate the upload action.
 */
public class UploadDocumentCommand implements Command {
    private DocumentRepository repository;
    private String fileName, docType, filePath;
    private JTextArea displayArea;

    public UploadDocumentCommand(DocumentRepository repository, String fileName, String docType, String filePath, JTextArea displayArea) {
        this.repository = repository;
        this.fileName = fileName;
        this.docType = docType;
        this.filePath = filePath;
        this.displayArea = displayArea;
    }

    @Override
    public void execute() {
        // Create a document using the Factory Pattern
        Document document = DocumentFactory.createDocument(docType, fileName);
        repository.addDocument(document); // Add document to repository
        displayArea.append("Uploaded Document: " + fileName + "\n");
    }
}
