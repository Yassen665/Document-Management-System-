package roles;

/**
 * Concrete class for Admin role.
 */
public class AdminRole implements UserRole {
    @Override
    public String getRole() {
        return "Admin";
    }
}
