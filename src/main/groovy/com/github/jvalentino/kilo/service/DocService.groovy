package com.github.jvalentino.kilo.service

import com.github.jvalentino.kilo.dto.DocDto
import com.github.jvalentino.kilo.dto.DocPair
import com.github.jvalentino.kilo.entity.DocTable
import com.github.jvalentino.kilo.entity.DocVersionTable
import com.github.jvalentino.kilo.repo.DocRepo
import com.github.jvalentino.kilo.repo.DocVersionRepo
import com.github.jvalentino.kilo.util.DateUtil
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.nio.ByteBuffer
import java.sql.Timestamp

/**
 * General doc services
 * @author john.valentino
 */
@CompileDynamic
@Service
@Slf4j
class DocService {

    @Autowired
    DocRepo docRepo

    @Autowired
    DocVersionRepo docVersionRepo

    DocPair process(DocDto event) {
        DocPair result = new DocPair()

        Optional<DocTable> optional = docRepo.findById(UUID.fromString(event.docId))
        DocTable doc = null

        if (optional.present) {
            log.info("Doc ${event.docId} exists, updating it")
            doc = optional.get()
            doc.with {
                name = event.fileName
                mimeType = event.mimeType
                updatedByUserId = event.userId
                updatedDateTime = new Timestamp(DateUtil.toDate(event.dateTime).time)
            }
        } else {
            log.info("Doc ${event.docId} does NOT exist, create a new doc record")
            doc = new DocTable()
            doc.with {
                docId = UUID.fromString(event.docId)
                name = event.fileName
                mimeType = event.mimeType
                createdByUserId = event.userId
                updatedByUserId = event.userId
                createdDateTime = new Timestamp(DateUtil.toDate(event.dateTime).time)
                updatedDateTime = new Timestamp(DateUtil.toDate(event.dateTime).time)
            }
        }

        result.docTable = doc
        docRepo.save(doc)

        // always a new version
        DocVersionTable version = new DocVersionTable()
        version.with {
            docVersionId = UUID.fromString(event.docVersionId)
            docId = UUID.fromString(event.docId)
            name = event.fileName
            mimeType = event.mimeType
            createdByUserId = event.userId
            createdDateTime = new Timestamp(DateUtil.toDate(event.dateTime).time)
            data = ByteBuffer.wrap(event.base64.decodeBase64())
        }

        result.docVersionTable = version
        docVersionRepo.save(version)

        result
    }

}
