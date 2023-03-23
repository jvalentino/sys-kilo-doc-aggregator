package com.github.jvalentino.kilo.listener

import com.github.jvalentino.kilo.dto.DocDto
import com.github.jvalentino.kilo.dto.DocPair
import com.github.jvalentino.kilo.entity.DocTable
import com.github.jvalentino.kilo.repo.DocRepo
import com.github.jvalentino.kilo.repo.DocVersionRepo
import com.github.jvalentino.kilo.util.BaseIntg
import com.github.jvalentino.kilo.util.DateUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

import static org.mockito.Mockito.verify

class DocListenerIntgTest extends BaseIntg {

    @MockBean
    DocRepo docRepo

    @MockBean
    DocVersionRepo docVersionRepo

    @Autowired
    DocListener docListener

    @Captor
    ArgumentCaptor<DocTable> docProducerCaptor

    void "test new document"() {
        given:
        String json = new File('src/test/resources/event-1.json').text
        ConsumerRecord<String, String> payload = GroovyMock()
        1 * payload.value() >> json

        and:
        Optional<DocTable> optional = GroovyMock()
        org.mockito.Mockito.when(
                docRepo.findById(UUID.fromString('789f2782-d5b8-417e-8982-abc18a7bbe03')))
                .thenReturn(optional)
        1 * optional.present >> false

        when:
        DocPair result = docListener.consume(payload)

        then:
        result.docTable.docId.toString() == '789f2782-d5b8-417e-8982-abc18a7bbe03'
        result.docTable.mimeType == 'application/pdf'
        result.docTable.name == 'test.pdf'
        result.docTable.updatedByUserId.toString() == '8ad96754-d281-403f-b767-c01f31ce470a'
        result.docTable.createdByUserId.toString() == '8ad96754-d281-403f-b767-c01f31ce470a'
        DateUtil.fromDate(new Date(result.docTable.createdDateTime.time)) == '2022-01-02T00:00:00.000+0000'
        DateUtil.fromDate(new Date(result.docTable.updatedDateTime.time)) == '2022-01-02T00:00:00.000+0000'

        result.docVersionTable.docVersionId.toString() == '50677e79-d6b3-462a-b48c-a52a30e2c61f'
        result.docVersionTable.docId.toString() == '789f2782-d5b8-417e-8982-abc18a7bbe03'
        result.docVersionTable.mimeType == 'application/pdf'
        result.docVersionTable.name == 'test.pdf'
        result.docVersionTable.createdByUserId.toString() == '8ad96754-d281-403f-b767-c01f31ce470a'
        DateUtil.fromDate(new Date(result.docVersionTable.createdDateTime.time)) == '2022-01-02T00:00:00.000+0000'
    }

}
