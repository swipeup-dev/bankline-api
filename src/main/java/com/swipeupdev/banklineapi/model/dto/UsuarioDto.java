package com.swipeupdev.banklineapi.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioDto {

    @NotBlank(message = "O nome de usuário não pode ser em branco.")
    @Size(min = 6, max = 100, message = "O nome do usuário deverá ser entre 6 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O login de usuário não pode ser em branco.")
    @Size(min = 4, max = 20, message = "O login de usuário deverá ser entre 4 e 20 caracteres.")
    private String login;

    @NotBlank(message = "O cpf do usuário não pode ser em branco.")
    @Pattern(regexp = "[\\d]{11}", message = "Digite apenas números.")
    private String cpf;

    @NotBlank(message = "A senha de suário não pode ser em branco.")
    @Size(min = 8)
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
