package com.github.jvalentino.kilo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.jvalentino.kilo.util.DateUtil
import groovy.transform.CompileDynamic

import java.sql.Timestamp

/**
 * Represents an individual version of a document
 * @author john.valentino
 */
@CompileDynamic
class DocVersion {

    String docVersionId

    String versionNum

    @JsonIgnore
    Doc doc

    byte[] data

    @JsonFormat(pattern= DateUtil.ISO)
    Timestamp createdDateTime

    Object createdByUser

    String createdByUserId

    DocVersion() { }

    DocVersion(Long docVersionId, Long versionNum, Date createdDateTime) {
        this.docVersionId = docVersionId
        this.versionNum = versionNum
        this.createdDateTime = new Timestamp(createdDateTime.time)
    }

}
