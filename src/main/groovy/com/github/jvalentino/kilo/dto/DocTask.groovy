package com.github.jvalentino.kilo.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.jvalentino.kilo.util.DateUtil
import groovy.transform.CompileDynamic

import java.sql.Timestamp

/**
 * A task associated with a document
 * @author john.valentino
 */
@CompileDynamic
class DocTask {

    String docTaskId

    Doc doc

    String name

    String status

    String content

    Object createdByUser

    String createdByUserId

    Object updatedByUser

    String updatedByUserId

    @JsonFormat(pattern= DateUtil.ISO)
    Timestamp createdDateTime

    @JsonFormat(pattern=DateUtil.ISO)
    Timestamp updatedDateTime

}
