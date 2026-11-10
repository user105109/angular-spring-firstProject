package org.punpun.demo.security.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.punpun.demo.security.entities.AppRole;
import org.punpun.demo.security.entities.AppUser;
import org.punpun.demo.security.repository.AppRoleRepository;
import org.punpun.demo.security.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(AppUser user) {

        if (user == null ||
                user.getUsername() == null ||
                user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (user.getPassword() == null ||
                user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        String username = user.getUsername().trim();

        if (appUserRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            String email = user.getEmail().trim();

            if (appUserRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Email already exists");
            }

            user.setEmail(email);
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return appUserRepository.save(user);
    }

    @Override
    public AppRole addNewRole(AppRole role) {

        if (role == null ||
                role.getName() == null ||
                role.getName().isBlank()) {
            throw new IllegalArgumentException("Role name is required");
        }

        String roleName = role.getName().trim();

        if (appRoleRepository.existsByName(roleName)) {
            throw new IllegalArgumentException("Role already exists");
        }

        role.setName(roleName);
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        AppRole role = appRoleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        user.getRoles().add(role);
    }

    @Override
    public AppRole removeRoleFromUser(String username, String roleName) {
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        AppRole role = appRoleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        if (user.getRoles().contains(role)) {
            throw new IllegalArgumentException("User doesn't have this role.");
        }

        user.getRoles().remove(role);
        return role;
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findWithRolesByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User with username: " + username + " is not found"
                        )
                );
    }

    @Override
    public boolean roleExists(String roleName) {
        return appRoleRepository.existsByName(roleName);
    }

    @Override
    public boolean userExists(String username) {
        return appUserRepository.existsByUsername(username);
    }
}
