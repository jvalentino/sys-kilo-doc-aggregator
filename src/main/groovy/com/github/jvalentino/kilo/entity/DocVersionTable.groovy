package com.github.jvalentino.kilo.entity

import groovy.transform.CompileDynamic
import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

import java.nio.ByteBuffer
import java.sql.Timestamp

/**
 * doc_version table in cassandra.
 * @author john.valentino
 */
@CompileDynamic
@Table(value='doc_version')
class DocVersionTable {

    @Id
    @PrimaryKey('doc_version_id')
    @PrimaryKeyColumn(name = 'doc_version_id', ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @Column(value = 'doc_version_id')
    UUID docVersionId

    @Column(value = 'doc_id')
    UUID docId

    @Column
    String name

    @Column(value = 'mime_type')
    String mimeType

    @Column(value = 'created_by_user_id')
    String createdByUserId

    @Column(value = 'created_date_time')
    Timestamp createdDateTime

    @Column
    ByteBuffer data

}
