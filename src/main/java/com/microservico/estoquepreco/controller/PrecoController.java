package com.microservico.estoquepreco.controller;

import com.microservico.estoquepreco.service.RabbitMqService;
import constantes.RabbitMqConstantes;
import dto.PrecoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    private RabbitMqService service;

    public PrecoController(RabbitMqService service) {
        this.service = service;
    }

    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDto precoDto){

        System.out.println(precoDto.getCodigoProduto());

        service.enviaMensagem(RabbitMqConstantes.FILA_PRECO,precoDto);
        return new ResponseEntity(HttpStatus.OK);

    }

}
