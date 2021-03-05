package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.dto.LancamentoDto;
import com.swipeupdev.banklineapi.model.entity.Conta;
import com.swipeupdev.banklineapi.model.entity.Lancamento;
import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.exception.EntityRequirementException;
import com.swipeupdev.banklineapi.model.security.UserSecurity;
import com.swipeupdev.banklineapi.repository.LancamentoRepository;
import com.swipeupdev.banklineapi.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private PlanoContaService planoContaService;

    @Autowired
    private Validator validator;

    @Autowired
    private UserSecurity userSecurity;

    @Transactional
    public void inserir(LancamentoDto dto) {
        validator.validate(dto);
        userSecurity.validateAuthenticantion(dto.getLogin());

        Usuario usuario = usuarioService.getUsuarioExistente(dto.getLogin());
        Conta conta = contaService.getContaUsuario(usuario);
        PlanoConta plano = planoContaService.getPlanoContaUsuario(dto.getPlanoConta(), usuario);

        if (plano.isTransferencia()) {
            throw new EntityRequirementException(
                    "Planos de conta de transferência não são permitidos para lançamentos na própria conta.");
        }

        Lancamento lancamento = novoLancamentoSimples(dto, conta, plano);
        lancamentoRepository.save(lancamento);
        contaService.atualizarSaldo(conta, lancamento);
    }

    private Lancamento novoLancamentoSimples(LancamentoDto dto, Conta conta, PlanoConta plano) {
        Lancamento lancamento = new Lancamento();
        lancamento.setConta(conta);
        lancamento.setDataLancamento(dto.getData());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setPlanoConta(plano);
        lancamento.setValor(dto.getValor());

        return lancamento;
    }
}
