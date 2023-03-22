package com.github.jvalentino.kilo.repo

import com.github.jvalentino.kilo.entity.DocVersionTable
import groovy.transform.CompileDynamic
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import org.springframework.stereotype.Repository

/**
 * Repo class for the doc table
 * @author john.valentino
 */
@CompileDynamic
@Repository
interface DocVersionRepo extends CassandraRepository<DocVersionTable, UUID> {

    @Query('select * from doc_version where doc_id = ?0')
    List<DocVersionTable> findByDocId(UUID docId)

}
