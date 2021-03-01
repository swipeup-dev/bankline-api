package com.swipeupdev.banklineapi.model.entity;

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
        @Index(name = "idx_usuario_cpf", columnList = "cpf", unique = true),
        @Index(name = "idx_usuario_login", columnList = "login", unique = true)
    }
)
public class Usuario implements Serializable {
    private static final long serialVersionUID = 7733776198250219608L;
    private static final byte NOME_LENGTH = 100;
    private static final byte CPF_LENGTH = 11;
    protected static final byte LOGIN_MIN_LENGTH = 3;
    protected static final byte LOGIN_MAX_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = NOME_LENGTH, nullable = false)
    @NotNull
    private String nome;

    @Column(name = "cpf", length = CPF_LENGTH, nullable = false)
    @Size(min = CPF_LENGTH, max = CPF_LENGTH)
    @NotNull
    private String cpf;

    @Column(name = "login", length = LOGIN_MAX_LENGTH, nullable = false)
    @Size(min = LOGIN_MIN_LENGTH, max = LOGIN_MAX_LENGTH)
    @NotNull
    private String login;

    @Lob
    @Column(name = "senha", nullable = false)
    @NotNull
    private String senha;

    @Lob
    @Column(name = "senha_recuperacao")
    @NotNull
    private String senhaRecuperacao;

    @Column(name = "recuperar_senha")
    @NotNull
    private Boolean recuperarSenha;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}