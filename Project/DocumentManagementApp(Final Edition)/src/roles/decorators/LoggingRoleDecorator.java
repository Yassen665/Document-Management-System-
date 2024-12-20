package roles.decorators;

import roles.UserRole;

/**
 * Concrete decorator for adding logging functionality to roles.
 */
public class LoggingRoleDecorator extends RoleDecorator {
    public LoggingRoleDecorator(UserRole role) {
        super(role);
    }

    @Override
    public String getRole() {
        System.out.println("Logging access for role: " + decoratedRole.getRole());
        return super.getRole();
    }
}
