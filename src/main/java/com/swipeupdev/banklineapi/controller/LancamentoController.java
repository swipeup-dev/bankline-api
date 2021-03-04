package com.swipeupdev.banklineapi.controller;

import com.swipeupdev.banklineapi.model.dto.LancamentoDto;
import com.swipeupdev.banklineapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @PostMapping
    public void inserir(@RequestBody LancamentoDto dto) {
        service.inserir(dto);
    }

}
