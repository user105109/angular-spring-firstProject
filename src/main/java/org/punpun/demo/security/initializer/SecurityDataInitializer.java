package org.punpun.demo.security.initializer;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.punpun.demo.security.entities.AppRole;
import org.punpun.demo.security.entities.AppUser;
import org.punpun.demo.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityDataInitializer implements CommandLineRunner {
    private final AccountService accountService;

    @Override
    public void run(String @NonNull ...args) {
        // Implementation for running the security data initialization
        createRoleIfMissing("ROLE_USER");
        createRoleIfMissing("ROLE_ADMIN");

        createUserIfMissing(
                "admin",
                "admin123",
                "admin@example.com"
        );
        createUserIfMissing(
                "employee",
                "employee123",
                "employee@example.com"
        );

        if (accountService.userExists("admin") && accountService.roleExists("ROLE_ADMIN") && accountService.roleExists("ROLE_USER")) {
            System.out.println(
                    "Admin exists: " + accountService.userExists("admin")
            );
            accountService.addRoleToUser("admin", "ROLE_ADMIN");
            accountService.addRoleToUser("admin", "ROLE_USER");
        }

        if (accountService.userExists("employee") && accountService.roleExists("ROLE_USER")) {
            System.out.println(
                    "Employee exists: " + accountService.userExists("employee")
            );
            accountService.addRoleToUser("employee", "ROLE_USER");
        }
    }

    private void createRoleIfMissing(String roleName) {
        if (!accountService.roleExists(roleName)) {
            accountService.addNewRole(AppRole.builder().name(roleName).build());
        }
    }

    private void createUserIfMissing(String username,
                                     String password,
                                     String email) {
        if (!accountService.userExists(username)) {
            accountService.addNewUser(AppUser.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build());
        }
    }
}
