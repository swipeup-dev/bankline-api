package com.swipeupdev.banklineapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class LancamentoDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd",
            timezone = "GMT")
    @NotNull(message = "A 'data' não foi informada.")
    private LocalDate data;

    @NotBlank(message = "A 'descricao' não pode ser em branco.")
    private String descricao;

    @NotBlank(message = UsuarioDto.LOGIN_NOT_BLANK)
    private String login;

    @NotNull(message = "O 'plano_conta' não foi informado.")
    @Positive(message = "Valores negativos não aceitos pelo campo 'plano_conta'.")
    private int planoConta;

    @NotNull(message = "O 'valor' não foi informado.")
    @Positive(message = "Valores menores iguais a zero não são aceitos pelo campo 'valor'.")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double valor;

    public LancamentoDto() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPlanoConta() {
        return planoConta;
    }

    public void setPlanoConta(int planoConta) {
        this.planoConta = planoConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
