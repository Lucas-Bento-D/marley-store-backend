package com.marley_store.stock_system.model.user;

import com.marley_store.stock_system.model.role.RoleName;

public record CreateUserDTO(
        String email,
        String password,
        RoleName role
) {
}
