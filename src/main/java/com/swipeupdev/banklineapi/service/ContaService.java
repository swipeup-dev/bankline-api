package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.entity.Conta;
import com.swipeupdev.banklineapi.model.entity.Lancamento;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.exception.EntityRequirementException;
import com.swipeupdev.banklineapi.model.exception.RecordNotFoundException;
import com.swipeupdev.banklineapi.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    protected void novaContaParaUsuario(Usuario usuario) {
        Conta conta = new Conta();
        conta.setUsuario(usuario);
        contaRepository.save(conta);
    }

    protected Conta getContaUsuario(Usuario usuario) {
        Optional<Conta> opt = contaRepository.findByUsuario(usuario);
        if (opt.isEmpty()) {
            throw new RecordNotFoundException("Conta não encontrada para este usuário.");
        }
        return opt.get();
    }

    protected double validarSaldoParaLancamento(Conta conta, Lancamento lancamento) {
        double saldoFuturo = conta.getSaldo() + lancamento.getValor();
        if (saldoFuturo < 0) {
            throw new EntityRequirementException("Saldo insuficiente.");
        }
        return saldoFuturo;
    }

    @Transactional
    protected void atualizarSaldo(Conta conta, Lancamento lancamento) {
        double saldo = this.validarSaldoParaLancamento(conta, lancamento);
        conta.setSaldo(saldo);
        contaRepository.save(conta);
    }
}
