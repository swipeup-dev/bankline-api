package com.swipeupdev.banklineapi.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {
    @NotBlank(message = UsuarioDto.LOGIN_NOT_BLANK)
    private String login;

    @NotBlank(message = UsuarioDto.SENHA_NOT_BLANK)
    @Size(min = UsuarioDto.SENHA_MIN_SIZE, message = UsuarioDto.SENHA_SIZE_MESSAGE)
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
