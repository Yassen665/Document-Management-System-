package commands;

import accesscontrol.UserAccessControl;
import factories.UserRoleFactory;
import roles.UserRole;

import javax.swing.*;

/**
 * Command for assigning roles to users.
 * Implements the Command Design Pattern to encapsulate role assignment logic.
 */
public class AssignRoleCommand implements Command {
    private UserAccessControl accessControl;
    private String userName, roleType;
    private JTextArea displayArea;

    public AssignRoleCommand(UserAccessControl accessControl, String userName, String roleType, JTextArea displayArea) {
        this.accessControl = accessControl;
        this.userName = userName;
        this.roleType = roleType;
        this.displayArea = displayArea;
    }

    @Override
    public void execute() {
        // Create role using the Factory Pattern
        UserRole role = UserRoleFactory.createRole(roleType);
        accessControl.assignRole(userName, role); // Assign role
        displayArea.append("Assigned role " + roleType + " to user " + userName + "\n");
    }
}
