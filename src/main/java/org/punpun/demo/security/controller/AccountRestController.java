package org.punpun.demo.security.controller;

import lombok.RequiredArgsConstructor;
import org.punpun.demo.security.dtos.*;
import org.punpun.demo.security.entities.AppRole;
import org.punpun.demo.security.entities.AppUser;
import org.punpun.demo.security.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;

    @GetMapping("/users/{username}")
    public AppUserDTO getUser(@PathVariable String username) {
        AppUser user = accountService.loadUserByUsername(username);
        Set<String> roles = user.getRoles().stream().map(AppRole::getName).collect(Collectors.toSet());
        return new AppUserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles
        );
    }

    @PostMapping("/users")
    public AppUserDTO saveUser(@RequestBody CreateUserDTO userDTO) {
        AppUser user = AppUser.builder()
                .username(userDTO.username())
                .password(userDTO.password())
                .email(userDTO.email())
                .build();
        AppUser savedUser = accountService.addNewUser(user);
        Set<String> roles = savedUser.getRoles().stream().map(AppRole::getName).collect(Collectors.toSet());
        return new AppUserDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                roles
        );
    }

    @PostMapping("/roles")
    public AppRoleDTO saveRole(@RequestBody CreateRoleDTO roleDTO) {
        AppRole role = AppRole.builder().name(roleDTO.roleName()).build();
        AppRole savedRole = accountService.addNewRole(role);
        return new AppRoleDTO(savedRole.getName());
    }

    @PostMapping("/users/{username}/roles/{roleName}")
    public void saveRoleForUser(@PathVariable String username, @PathVariable String roleName) {
        accountService.addRoleToUser(username, roleName);
    }

    @DeleteMapping("/users/{username}/roles/{roleName}")
    public AppRole removeRoleFromUser(@PathVariable String username, @PathVariable String roleName) {
        return accountService.removeRoleFromUser(username, roleName);
    }
}
