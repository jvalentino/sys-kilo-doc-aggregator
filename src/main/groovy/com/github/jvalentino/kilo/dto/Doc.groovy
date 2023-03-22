package com.github.jvalentino.kilo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.jvalentino.kilo.util.DateUtil
import groovy.transform.CompileDynamic

import java.sql.Timestamp

/**
 * represents the document
 * @author john.valentino
 */
@CompileDynamic
class Doc {

    String docId

    String name

    String mimeType

    Object createdByUser

    String createdByUserId

    Object updatedByUser

    String updatedByUserId

    @JsonFormat(pattern= DateUtil.ISO)
    Timestamp createdDateTime

    @JsonFormat(pattern=DateUtil.ISO)
    Timestamp updatedDateTime

    Set<DocVersion> versions

    Set<DocTask> tasks

}
