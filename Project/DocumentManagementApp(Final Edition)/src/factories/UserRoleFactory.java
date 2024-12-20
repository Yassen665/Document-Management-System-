package factories;

import roles.AdminRole;
import roles.EditorRole;
import roles.ViewerRole;
import roles.UserRole;

/**
 * Factory class for creating user role objects based on the role type.
 * Implements the Factory Design Pattern to simplify role creation.
 */
public class UserRoleFactory {
    /**
     * Creates a user role instance based on the role type.
     * @param roleType The type of the role (e.g., "admin", "editor", "viewer").
     * @return An instance of the appropriate UserRole subclass.
     */
    public static UserRole createRole(String roleType) {
        switch (roleType.toLowerCase()) {
            case "admin":
                return new AdminRole();
            case "editor":
                return new EditorRole();
            case "viewer":
                return new ViewerRole();
            default:
                throw new IllegalArgumentException("Invalid role type: " + roleType);
        }
    }
}
