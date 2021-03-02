package com.swipeupdev.banklineapi.controller;

import com.swipeupdev.banklineapi.model.dto.LoginDto;
import com.swipeupdev.banklineapi.model.dto.UsuarioDto;
import com.swipeupdev.banklineapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto inserir(@RequestBody UsuarioDto dto){
        return service.inserir(dto);
    }

    @PostMapping(path = "/nova-senha")
    public ResponseEntity<?> novaSenha() {
        return null;
    }

    @PostMapping(path = "/alterar-senha")
    public ResponseEntity<?> alterarSenha(LoginDto dto) {
        return null;
    }

}
