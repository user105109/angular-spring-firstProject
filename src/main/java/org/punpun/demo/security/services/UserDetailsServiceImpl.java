package org.punpun.demo.security.services;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.punpun.demo.security.entities.AppRole;
import org.punpun.demo.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        AppUser user = accountService.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " is not found");
        }

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        user.getRoles()
                                .stream()
                                .map(AppRole::getName)
                                .toArray(String[]::new))
                .build();
    }
}
