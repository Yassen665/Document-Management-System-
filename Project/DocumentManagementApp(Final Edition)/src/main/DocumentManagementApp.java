package main;

import repository.DocumentRepository;
import accesscontrol.UserAccessControl;
import observer.DocumentObserver;
import observer.DocumentListObserver;
import commands.Command;
import commands.UploadDocumentCommand;
import commands.AssignRoleCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Entry point of the Document Management Application.
 * Manages the GUI and coordinates actions for uploading documents and assigning roles.
 */
public class DocumentManagementApp {
    // Singleton instances for managing documents and roles
    private static DocumentRepository repository = DocumentRepository.getInstance();
    private static UserAccessControl accessControl = UserAccessControl.getInstance();

    public static void main(String[] args) {
        // Observer Pattern: Add an observer to log document additions
        DocumentObserver observer = new DocumentListObserver();
        repository.addObserver(observer);

        // GUI Setup
        JFrame frame = new JFrame("Document Management App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Panel for input controls
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Input fields for uploading documents
        JLabel docLabel = new JLabel("Add a Document:");
        JButton uploadFileButton = new JButton("Upload File");
        JTextArea documentListArea = new JTextArea(10, 30); // Displays uploaded documents
        documentListArea.setEditable(false);

        panel.add(docLabel);
        panel.add(uploadFileButton);

        // Input fields for assigning roles to users
        JLabel userLabel = new JLabel("Assign a Role to a User:");
        JTextField userNameField = new JTextField("User Name", 15);
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"admin", "viewer", "editor"});
        JButton assignRoleButton = new JButton("Assign Role");
        JTextArea userRoleArea = new JTextArea(10, 30); // Displays assigned roles
        userRoleArea.setEditable(false);

        panel.add(userLabel);
        panel.add(userNameField);
        panel.add(roleCombo);
        panel.add(assignRoleButton);

        // Event listener for the "Upload File" button
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // File chooser dialog
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    String fileName = fileChooser.getSelectedFile().getName();
                    String docType = getFileExtension(fileName);

                    // Command Pattern: Encapsulate the upload action
                    Command uploadCommand = new UploadDocumentCommand(repository, fileName, docType, filePath, documentListArea);
                    uploadCommand.execute();
                }
            }
        });

        // Event listener for the "Assign Role" button
        assignRoleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                String roleType = (String) roleCombo.getSelectedItem();

                // Command Pattern: Encapsulate the role assignment action
                Command assignCommand = new AssignRoleCommand(accessControl, userName, roleType, userRoleArea);
                assignCommand.execute();
            }
        });

        // Adding scrollable areas to display outputs
        JScrollPane docScrollPane = new JScrollPane(documentListArea);
        JScrollPane userScrollPane = new JScrollPane(userRoleArea);

        // Frame layout configuration
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(docScrollPane, BorderLayout.CENTER);
        frame.add(userScrollPane, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    /**
     * Helper method to extract the file extension from the file name.
     * @param fileName The name of the file.
     * @return The file extension as a string.
     */
    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0) {
            return fileName.substring(lastIndex + 1).toLowerCase();
        }
        return "";
    }
}
