package com.swipeupdev.banklineapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
        name = "tb_usuario",
        indexes = {
                @Index(name = "unq_idx_usuario_cpf", columnList = "cpf", unique = true),
                @Index(name = "unq_idx_usuario_login", columnList = "login", unique = true),
                @Index(name = "unq_idx_usuario_email", columnList = "email", unique = true)
        }
)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 7733776198250219608L;
    private static final byte NOME_LENGTH = 100;
    private static final byte CPF_LENGTH = 11;
    private static final byte LOGIN_MIN_LENGTH = 3;
    private static final byte LOGIN_MAX_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", length = NOME_LENGTH, nullable = false)
    @NotNull
    private String nome;

    @Column(name = "cpf", length = CPF_LENGTH, nullable = false)
    @Size(min = CPF_LENGTH, max = CPF_LENGTH)
    @NotNull
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", length = LOGIN_MAX_LENGTH, nullable = false)
    @Size(min = LOGIN_MIN_LENGTH, max = LOGIN_MAX_LENGTH)
    @NotNull
    private String login;

    @JsonIgnore
    @Lob
    @Column(name = "senha", nullable = false)
    @NotNull
    private String senha;

    @JsonIgnore
    @Lob
    @Column(name = "senha_recuperacao")
    private String senhaRecuperacao;

    @Column(name = "recuperar_senha", nullable = false)
    private Boolean recuperarSenha;

    public Usuario() {
        this.recuperarSenha = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSenhaRecuperacao() {
        return senhaRecuperacao;
    }

    public void setSenhaRecuperacao(String senhaRecuperacao) {
        this.senhaRecuperacao = senhaRecuperacao;
    }

    public Boolean getRecuperarSenha() {
        return recuperarSenha;
    }

    public void setRecuperarSenha(Boolean recuperarSenha) {
        this.recuperarSenha = recuperarSenha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}