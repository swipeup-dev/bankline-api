package com.swipeupdev.banklineapi.controller;

import com.swipeupdev.banklineapi.model.dto.AtualizadorSenhaDto;
import com.swipeupdev.banklineapi.model.dto.NovaSenhaDto;
import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void inserir(@RequestBody UsuarioDto dto) {
        service.inserir(dto);
    }

    @PostMapping(path = "/nova-senha")
    public ResponseEntity<NovaSenhaDto> novaSenha(@RequestBody NovaSenhaDto dto) {
        return ResponseEntity.ok(service.novaSenha(dto));
    }

    @PostMapping(path = "/alterar-senha")
    @ResponseStatus(HttpStatus.OK)
    public void alterarSenha(@RequestBody AtualizadorSenhaDto dto) {
        service.alterarSenha(dto);
    }

}
