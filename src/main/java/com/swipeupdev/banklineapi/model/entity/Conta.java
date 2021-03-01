package com.swipeupdev.banklineapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "saldo", nullable = false, precision = 30, scale = 2)
    @NotNull
    private Double saldo;

    @Column(name = "login_usuario", length = Usuario.LOGIN_MAX_LENGTH, nullable = false)
    @Size(min = Usuario.LOGIN_MIN_LENGTH, max = Usuario.LOGIN_MAX_LENGTH)
    @NotNull
    private String login;

    public Conta() {
        this.saldo = .0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return id.equals(conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
