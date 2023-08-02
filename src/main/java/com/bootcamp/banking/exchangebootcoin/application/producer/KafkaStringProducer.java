package com.bootcamp.banking.exchangebootcoin.application.producer;

import com.bootcamp.banking.exchangebootcoin.domain.dto.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaStringProducer {

//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStringProducer.class);

    private final KafkaTemplate<String, Transfer> kafkaTemplate;

    public KafkaStringProducer(KafkaTemplate<String, Transfer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Transfer transfer) {
        System.out.println(">>>>>> " + transfer.toString());
//        this.kafkaTemplate.send("BOOTCOIN-EXCHANGE", message);
        Message<Transfer> message = MessageBuilder
            .withPayload(transfer)
            .setHeader(KafkaHeaders.TOPIC, "BOOTCOIN-EXCHANGE")
            .build();
        this.kafkaTemplate.send(message);
    }

}