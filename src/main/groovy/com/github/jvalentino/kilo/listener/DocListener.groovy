package com.github.jvalentino.kilo.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.jvalentino.kilo.dto.DocDto
import com.github.jvalentino.kilo.service.DocService
import groovy.transform.CompileDynamic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
 * Listens on the doc topic
 * @author john.valentino
 */
@CompileDynamic
@Service
class DocListener {

    @Value('${topic.name.consumer')
    String topicName

    @Value('${server.kafka.consumer.group-id}')
    String groupId

    @Autowired
    DocService docService

    @KafkaListener(topics = '${topic.name.consumer}', groupId = '${server.kafka.consumer.group-id}')
    void consume(ConsumerRecord<String, String> payload) {
        String json = payload.value()
        DocDto doc = toObject(json, DocDto)
        docService.process(doc)
    }

    Object toObject(String json, Class clazz) {
        new ObjectMapper().readValue(json, clazz)
    }

}
