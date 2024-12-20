package roles.decorators;

import roles.UserRole;

/**
 * Abstract decorator class for dynamically adding behavior to user roles.
 */
public abstract class RoleDecorator implements UserRole {
    protected UserRole decoratedRole;

    public RoleDecorator(UserRole role) {
        this.decoratedRole = role;
    }

    @Override
    public String getRole() {
        return decoratedRole.getRole();
    }
}
