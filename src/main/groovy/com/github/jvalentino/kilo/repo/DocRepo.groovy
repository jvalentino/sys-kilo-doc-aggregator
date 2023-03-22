package com.github.jvalentino.kilo.repo

import com.github.jvalentino.kilo.entity.DocTable
import groovy.transform.CompileDynamic
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

/**
 * Repo class for the doc table
 * @author john.valentino
 */
@CompileDynamic
@Repository
interface DocRepo extends CassandraRepository<DocTable, UUID> {

}
