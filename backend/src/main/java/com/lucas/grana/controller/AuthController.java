package com.lucas.grana.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping("/hello")
    public String hello(){
        return "Olá, usuário autenticado!";
    }

    @GetMapping("/hello/admin")
        public String adminHello(){
            return "Olá, usuário administrador!";
        }
}
