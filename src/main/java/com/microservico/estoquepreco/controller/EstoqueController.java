package com.microservico.estoquepreco.controller;

import com.microservico.estoquepreco.service.RabbitMqService;
import constantes.RabbitMqConstantes;
import dto.EstoqueDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    private RabbitMqService service;

    public EstoqueController(RabbitMqService service) {
        this.service = service;
    }

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDto estoqueDto){

        System.out.println(estoqueDto.getCodigoProduto());
        service.enviaMensagem(RabbitMqConstantes.FILA_ESTOQUE,estoqueDto);
        return new ResponseEntity(HttpStatus.OK);

    }

}
