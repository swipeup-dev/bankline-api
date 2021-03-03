package com.swipeupdev.banklineapi.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsuarioDto {
    public static final String LOGIN_NOT_BLANK = "O 'login' do usuário não pode ser em branco.";

    @NotBlank(message = "O 'nome' do usuário não pode ser em branco.")
    @Size(min = 6, max = 100, message = "O 'nome' do usuário deverá ser entre 6 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O 'cpf' do usuário não pode ser em branco.")
    @Size(min = 11, max = 11, message = "O 'cpf' deve conter apenas 11 números.")
    @Pattern(regexp = "[\\d]{11}", message = "O 'cpf' deve conter apenas caracteres numéricos.")
    private String cpf;

    @Email(message = "Campo 'email' inválido.")
    @NotBlank(message = "O 'email' do usuário não pode ser em branco.")
    private String email;

    @NotBlank(message = LOGIN_NOT_BLANK)
    @Size(min = 4, max = 20, message = "O 'login' de usuário deverá ser entre 4 e 20 caracteres.")
    private String login;

    @NotBlank(message = "A 'senha' do usuário não pode ser em branco.")
    @Size(min = 8, message = "A 'senha' do usuário deve ser maior igual a 8 caracteres.")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
