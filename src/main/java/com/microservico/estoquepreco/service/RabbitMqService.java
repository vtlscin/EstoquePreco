package com.microservico.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private RabbitTemplate template;

    public RabbitMqService(RabbitTemplate template) {
        this.template = template;
    }

    public void enviaMensagem(String nomeFila, Object mensagem){

        this.template.convertAndSend(nomeFila,mensagem);

    }

}
