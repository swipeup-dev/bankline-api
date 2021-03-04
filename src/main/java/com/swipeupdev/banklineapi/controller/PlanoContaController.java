package com.swipeupdev.banklineapi.controller;

import com.swipeupdev.banklineapi.model.dto.PlanoContaDto;
import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.service.PlanoContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planos-conta")
public class PlanoContaController {
    @Autowired
    private PlanoContaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(@RequestBody PlanoContaDto dto) {
        service.inserir(dto);
    }
}
