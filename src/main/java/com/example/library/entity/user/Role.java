package com.example.library.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    READER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
