package com.swipeupdev.banklineapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AtualizadorSenhaDto {
    @NotBlank(message = UsuarioDto.LOGIN_NOT_BLANK)
    private String login;

    @NotBlank(message = "A 'nova_senha' do usuário não pode ser em branco.")
    @Size(min = UsuarioDto.SENHA_MIN_SIZE, message = "A 'nova_senha' do usuário deve ser maior igual a 8 caracteres.")
    private String novaSenha;

    @NotBlank(message = "A 'senha_recuperacao' não pode ser branco.")
    private String senhaRecuperacao;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty(value = "nova_senha")
    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    @JsonProperty(value = "senha_recuperacao")
    public String getSenhaRecuperacao() {
        return senhaRecuperacao;
    }

    public void setSenhaRecuperacao(String senhaRecuperacao) {
        this.senhaRecuperacao = senhaRecuperacao;
    }
}
