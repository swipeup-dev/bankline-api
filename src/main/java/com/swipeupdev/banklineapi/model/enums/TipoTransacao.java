package com.swipeupdev.banklineapi.model.enums;

public enum TipoTransacao {
    ENTRADA("ENTRADA", 1),
    SAIDA("SAIDA", -1);

    private String transacao;
    private int fatorConversao;

    private TipoTransacao(String transacao, int fatorConversao) {
        this.transacao = transacao;
        this.fatorConversao = fatorConversao;
    }
    
    public String getTransacao() {
        return transacao;
    }

    public int getFatorConversao() {
        return fatorConversao;
    }

    public static TipoTransacao parse(String transacao) {
        for (TipoTransacao tt: TipoTransacao.values()) {
            if (tt.transacao.equalsIgnoreCase(transacao)) {
                return tt;
            }
        }

        throw new IllegalArgumentException("TipoTransação inválida.");
    }
}
