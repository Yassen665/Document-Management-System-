package roles;

public class ViewerRole implements UserRole {
    @Override
    public String getRole() {
        return "Viewer";
    }
}
