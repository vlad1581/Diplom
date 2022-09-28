package com.example.toDo.models;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Roles implements GrantedAuthority {
    User,Admin;

    @Override
    public String getAuthority() {
        return name();
    }
}
