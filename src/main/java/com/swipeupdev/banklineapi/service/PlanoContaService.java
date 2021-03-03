package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.enums.TipoTransacao;
import com.swipeupdev.banklineapi.repository.PlanoContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PlanoContaService {
    @Autowired
    private PlanoContaRepository planoContaRepository;

    protected void novosPlanoContaPadrao(Usuario usuario) {
        PlanoConta p1 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_R, usuario, TipoTransacao.ENTRADA);
        PlanoConta p2 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_D, usuario, TipoTransacao.SAIDA);
        PlanoConta p3 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_TC, usuario, TipoTransacao.SAIDA);
        PlanoConta p4 = novoPlanoContaPadrao(PlanoConta.PLANO_PADRAO_TU, usuario, TipoTransacao.SAIDA);

        planoContaRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    }

    private PlanoConta novoPlanoContaPadrao(String desc, Usuario usuario, TipoTransacao tt) {
        return novoPlanoConta(desc, usuario, true, tt);
    }

    private PlanoConta novoPlanoContaUsuario(String desc, Usuario usuario, TipoTransacao tt) {
        return novoPlanoConta(desc, usuario, false, tt);
    }

    private PlanoConta novoPlanoConta(String desc, Usuario user, boolean padrao, TipoTransacao tt) {
        PlanoConta pc = new PlanoConta();
        pc.setDescricao(desc);
        pc.setUsuario(user);
        pc.setPadrao(padrao);
        pc.setTipoTransacao(tt);

        return pc;
    }
}
