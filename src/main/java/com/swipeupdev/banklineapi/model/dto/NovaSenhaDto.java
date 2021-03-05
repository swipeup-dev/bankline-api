package com.swipeupdev.banklineapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class NovaSenhaDto {

    @NotBlank(message = UsuarioDto.LOGIN_NOT_BLANK)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String login;

    @ApiModelProperty(hidden = true)
    private String senhaRecuperacao;

    public NovaSenhaDto() {
        senhaRecuperacao = UUID.randomUUID().toString().replace("-", "");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty(value = "senha_recuperacao")
    public String getSenhaRecuperacao() {
        return senhaRecuperacao;
    }

}
