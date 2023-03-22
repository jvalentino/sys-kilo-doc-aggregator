package com.github.jvalentino.kilo.dto

import groovy.transform.CompileDynamic

/**
 * Represents an uploaded document
 */
@CompileDynamic
class DocDto {

    String fileName
    String base64
    String mimeType
    String userId
    String docId
    String dateTime
    String docVersionId

}
