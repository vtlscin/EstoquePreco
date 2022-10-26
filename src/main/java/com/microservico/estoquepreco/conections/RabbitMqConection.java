package com.microservico.estoquepreco.conections;

import constantes.RabbitMqConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConection {

    public RabbitMqConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }
    private AmqpAdmin amqpAdmin;

    private static final String NOME_EXCHANGE = "amq.direct";

    private Queue fila(String nomeFila){
        return new Queue(nomeFila,true, false,false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE,
                troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitMqConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMqConstantes.FILA_PRECO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque,troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco,troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
