package com.swipeupdev.banklineapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_lancamento")
public class Lancamento implements Serializable {
    private static final long serialVersionUID = 5289248983087628100L;
    private static final byte VALOR_PRECISION = 18;
    private static final byte VALOR_SCALE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
            timezone = "GMT")
    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private Instant dataCadastro;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "descricao", length = 100, nullable = false)
    @NotBlank(message = "Descrição não pode ser em branco.")
    private String descricao;

    @Column(name = "valor", precision = VALOR_PRECISION, scale = VALOR_SCALE)
    private Double valor;

    @JsonIgnore
    @ManyToOne(targetEntity = Conta.class)
    @JoinColumn(name = "conta_id", referencedColumnName = "id", nullable = false)
    private Conta conta;

    @ManyToOne(targetEntity = PlanoConta.class)
    @JoinColumn(name = "plano_conta_id", referencedColumnName = "id", nullable = false)
    private PlanoConta planoConta;

    @OneToOne(targetEntity = Lancamento.class)
    @JoinColumn(name = "transferencia", referencedColumnName = "id")
    private Lancamento transferencia;

    public Lancamento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor * planoConta.getTipoTransacao().getFatorConversao();
    }

    public void setValor(Double valor) {
        this.valor = Math.abs(valor);
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public PlanoConta getPlanoConta() {
        return planoConta;
    }

    public void setPlanoConta(PlanoConta planoConta) {
        this.planoConta = planoConta;
    }

    public Lancamento getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Lancamento transferencia) {
        this.transferencia = transferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lancamento that = (Lancamento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
