package accesscontrol;

import roles.UserRole;

import java.util.HashMap;
import java.util.Map;

public class UserAccessControl {
    private static UserAccessControl instance;
    private Map<String, UserRole> userRoles = new HashMap<>();

    private UserAccessControl() {}

    public static synchronized UserAccessControl getInstance() {
        if (instance == null) {
            instance = new UserAccessControl();
        }
        return instance;
    }

    public void assignRole(String userName, UserRole role) {
        userRoles.put(userName, role);
    }

    public UserRole getRole(String userName) {
        return userRoles.get(userName);
    }
}
