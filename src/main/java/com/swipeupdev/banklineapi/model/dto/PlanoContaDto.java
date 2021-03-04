package com.swipeupdev.banklineapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.enums.TipoTransacao;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PlanoContaDto {
    @NotBlank(message = "A 'descricao' não pode ser em branco.")
    @Size(min = 3, max = PlanoConta.PLANO_CONTA_DESC_LENGTH,
            message = "A 'descricao' deve ter entre 3 e " + PlanoConta.PLANO_CONTA_DESC_LENGTH + " caracteres.")
    private String descricao;

    @NotBlank(message = UsuarioDto.LOGIN_NOT_BLANK)
    private String login;

    @NotBlank(message = "O 'tipo_transacao' não pode ser em branco.")
    private String tipoTransacao;

    @ApiModelProperty(hidden = true)
    private boolean padrao;

    public String getDescricao() {
        return descricao.toUpperCase();
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

    @JsonProperty(value = "tipo_transacao")
    public TipoTransacao getTipoTransacao() {
        return TipoTransacao.parse(tipoTransacao);
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Boolean getPadrao() {
        return padrao;
    }

    public void setPadrao(Boolean padrao) {
        this.padrao = padrao;
    }
}
