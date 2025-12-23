package org.gdg.techsprint.placement.readiness.backend.dto;
public class RegisterRequest {

    private String name = "User";
    private String role = "USER";
    private String email;
    private String password;

    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
