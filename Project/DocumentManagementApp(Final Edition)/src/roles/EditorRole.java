package roles;

public class EditorRole implements UserRole {
    @Override
    public String getRole() {
        return "Editor";
    }
}
