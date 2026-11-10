package org.punpun.demo.security.services;
import org.punpun.demo.security.entities.AppRole;
import org.punpun.demo.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(AppUser user);
    AppRole addNewRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppRole removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    boolean roleExists(String roleName);
    boolean userExists(String roleName);
}
