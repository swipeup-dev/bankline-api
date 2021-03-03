package com.swipeupdev.banklineapi.service;

import com.swipeupdev.banklineapi.model.entity.Conta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    protected void novaContaParaUsuario(Usuario usuario) {
        Conta conta = new Conta();
        conta.setUsuario(usuario);
        contaRepository.save(conta);
    }
}
