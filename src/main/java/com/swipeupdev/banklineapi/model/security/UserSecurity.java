package com.swipeupdev.banklineapi.model.security;

import com.swipeupdev.banklineapi.model.dto.SessaoDto;

public interface UserSecurity {
    String getKey();
    long getExpiration();
    String getPrefix();
    void validateAuthenticantion(String login);
    String generateToken(SessaoDto session);
}
