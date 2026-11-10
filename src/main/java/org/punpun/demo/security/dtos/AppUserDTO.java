package org.punpun.demo.security.dtos;

import java.util.Set;

public record AppUserDTO(Long id, String username, String email, Set<String> roles) {}
