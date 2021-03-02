package com.swipeupdev.banklineapi.model.entity;

import com.swipeupdev.banklineapi.model.enums.TipoTransacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(
    name = "tb_plano_conta",
    uniqueConstraints = {
        @UniqueConstraint(name = "unq_plano_conta_descricao_login", columnNames = {"descricao", "login_usuario"})
    }
)
public class PlanoConta implements Serializable {
    private static final long serialVersionUID = -8998598427364090544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao", length = 100, nullable = false)
    @NotBlank(message = "Descrição não pode ser em branco.")
    private String descricao;

    @Column(name = "tipo_transacao", length = 20)
    @NotBlank
    private String tipoTransacao;

    @Column(name = "padrao", nullable = false)
    private Boolean padrao;

    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "login_usuario", referencedColumnName = "login", nullable = false)
    private Usuario login;

    public PlanoConta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipoTransacao() {
        return TipoTransacao.parse(tipoTransacao);
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao.getTransacao();
    }

    public Boolean getPadrao() {
        return padrao;
    }

    public void setPadrao(Boolean padrao) {
        this.padrao = padrao;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoConta that = (PlanoConta) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
